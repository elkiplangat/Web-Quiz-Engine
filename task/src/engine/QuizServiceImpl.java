package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class QuizServiceImpl implements QuizService{
    private final QuizRepository quizRepository;

    public QuizServiceImpl(@Autowired QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public HashMap<String, Object> getQuizzes(int pageNo) {
        Pageable paging = PageRequest.of(pageNo, 10);

        Page<Quiz> pagedResult = quizRepository.findAll(paging);

            HashMap<String, Object> content = new HashMap<>();
            content.put("totalPages",pagedResult.getTotalPages());
            content.put("totalElements",pagedResult.getTotalElements());
            content.put("last",pagedResult.isLast());
            content.put("first",pagedResult.isFirst());
            content.put("sort", new int[]{});
            content.put("number", pageNo);
            content.put("numberOfElements", pagedResult.getNumberOfElements());
            content.put("size", 10);
            content.put("empty", pagedResult.isEmpty());
            content.put("pageable", new int[]{});
            content.put("content", pagedResult.getContent());


            return content;
       
    }

    @Override
    public Quiz getQuizById(int id) {
        return quizRepository.findById(id).get();
    }

    @Override
    public void insert(Quiz quiz, User user) {
        quiz.setUser(user);
        quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(int id, String username) throws IllegalAccessException {
        if (quizRepository.existsById(id)) {
            if (quizRepository.findById(id).get().getUser().getEmail().equals(username)) {
                    quizRepository.deleteById(id);
            }else {
                throw new IllegalAccessException("not allowed!");
            }

        }else {
            throw new IllegalStateException("No such quiz");
        }
    }

}
