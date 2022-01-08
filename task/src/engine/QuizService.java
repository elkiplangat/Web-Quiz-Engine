package engine;

import java.util.HashMap;


public interface QuizService{
    HashMap<String, Object> getQuizzes(int pageNo);
    Quiz getQuizById(int id);
    void insert(Quiz quiz, User user);

    void deleteQuiz(int id, String username) throws IllegalAccessException;
}
