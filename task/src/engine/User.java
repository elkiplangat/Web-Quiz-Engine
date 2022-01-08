package engine;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull
    @NotBlank
    @NotEmpty
    @Email
    @Pattern(regexp = ".*\\..*")
    @Column
    private String email;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 5)
    private String password;

    public String getEmail() {
        return email;
    }

    @OneToMany(mappedBy = "user" )
    private List<Quiz> quizzes;

    public String getPassword() {
        return this.password;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }
}
