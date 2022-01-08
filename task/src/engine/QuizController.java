package engine;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@Validated
public class QuizController {
    QuizService quizService;
    CompletedService completedService;


    public QuizController(QuizService quizService,CompletedService completedService) {
        this.quizService = quizService;
        this.completedService = completedService;
    }

    public volatile static ArrayList<Quiz> quizzes = new ArrayList<>();

    //Quiz quiz = new Quiz("The Java Logo", "What is depicted in the Java logo?", new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"}, 2);
    HashMap<String, Object> responseSuccess = new HashMap<String, Object>(

            Map.of("success", true, "feedback", "Congratulations, you're right!")
    );
    Map<String, Object> responseFailed = new HashMap<String, Object>(

            Map.of("success", false, "feedback", "Wrong answer! Please, try again.")
    );

//  @GetMapping("/api/quiz")
//  public ResponseEntity<Quiz> getQuiz(){
//        return new ResponseEntity<>(quiz, HttpStatus.OK);
//  }

    @PostMapping("api/quizzes/{id}/solve")
    public ResponseEntity<HashMap<String, Object>> postAnswer(@PathVariable long id, @RequestBody HashMap<String, Integer[]> answer,  @AuthenticationPrincipal UserDetailsImpl user) {
        Integer[] receivedAnswer = answer.get("answer");
        System.out.println("COMECOME COME: "+id);
        System.out.println("COMECOME COME: "+(int)id);

        try {
            Quiz gottemQuiz = quizService.getQuizById((int) id);
            if (receivedAnswer.length > 0) {
                if (Arrays.asList(receivedAnswer).equals(gottemQuiz.getAnswer())) {

                    completedService.insert(gottemQuiz.getId(), user.getUser().getId());
                    return new ResponseEntity<>((HashMap<String, Object>) responseSuccess, HttpStatus.OK);
                } else {
                    return new ResponseEntity<HashMap<String, Object>>((HashMap<String, Object>) responseFailed, HttpStatus.OK);
                }
            } else {
                if (gottemQuiz.getAnswer().size() == 0) {

                    completedService.insert(gottemQuiz.getId(), user.getUser().getId());
                    return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>((HashMap<String, Object>) responseFailed, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("api/quizzes")
    ResponseEntity<Quiz> addQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal UserDetailsImpl user) {
        quizService.insert(quiz, user.getUser());
        return new ResponseEntity<Quiz>(quiz, HttpStatus.OK);
    }

    @GetMapping("api/quizzes")
    ResponseEntity<HashMap<String, Object>> getAllQuizzes(@RequestParam(defaultValue = "0") Integer page) {
        HashMap<String, Object> content = quizService.getQuizzes(page);


        return new ResponseEntity<HashMap<String, Object>>(content, HttpStatus.OK);
    }


    @GetMapping("api/quizzes/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable long id) {
        try {

            Quiz gottenQuiz = quizService.getQuizById((int) id);

            return new ResponseEntity<Quiz>(gottenQuiz, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("api/quizzes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable int id, @AuthenticationPrincipal UserDetails user) {
        try {
            quizService.deleteQuiz(id, user.getUsername());
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
