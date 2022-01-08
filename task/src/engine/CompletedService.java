package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


public interface CompletedService {



    HashMap<String, Object> getCompleted(int pageNo, Long userId);

    void insert(int completedId, Long userId);

    void deleteCompleted(int id, String userId) throws IllegalAccessException;

}
