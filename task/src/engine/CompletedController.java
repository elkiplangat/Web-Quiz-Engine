package engine;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController

public class CompletedController {
    private final CompletedService completedService;

    public CompletedController(CompletedService completedService) {
        this.completedService = completedService;
    }

    @GetMapping("api/quizzes/completed")
    public ResponseEntity<HashMap<String, Object>> getCompleted(@RequestParam(defaultValue = "0") Integer page,@AuthenticationPrincipal UserDetailsImpl user) {
        return new ResponseEntity<HashMap<String, Object>>(completedService.getCompleted(page, user.getUser().getId()), HttpStatus.OK);

    }
}
