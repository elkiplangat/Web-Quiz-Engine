package engine;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompletedRepository extends PagingAndSortingRepository<Completed, Integer> {
    List<Completed> findByUserIdOrderByCompletedAtDesc(Long userId);
}
