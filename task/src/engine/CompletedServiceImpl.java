package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CompletedServiceImpl implements CompletedService{

    @Autowired
    private final CompletedRepository completedRepository;

    public CompletedServiceImpl(@Autowired CompletedRepository completedRepository) {
        this.completedRepository = completedRepository;
    }


    public List<Completed> findByUserId(Long userId) {
        return completedRepository.findByUserIdOrderByCompletedAtDesc(userId);
    }

    @Override
    public HashMap<String, Object> getCompleted(int pageNo, Long userId) {
      List<Completed> completed = completedRepository.findByUserIdOrderByCompletedAtDesc(userId);
        PagedListHolder page  = new PagedListHolder(completed);
        page.setPageSize(10);
        page.setPage(pageNo);

        HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("totalPages", page.getPageCount());
            hashMap.put("totalElements", completed.size());
            hashMap.put("last", page.isLastPage());
            hashMap.put("first", page.isFirstPage());
            hashMap.put("content", page.getPageList());




        return hashMap;
    }

    @Override
    public void insert(int id, Long userId) {
        Completed completed = new Completed();
        completed.setId(id);
        completed.setCompletedAt(new Timestamp(System.currentTimeMillis()));
        completed.setUserId(userId);

        completedRepository.save(completed);



    }

    @Override
    public void deleteCompleted(int id, String userId) throws IllegalAccessException {

    }
}
