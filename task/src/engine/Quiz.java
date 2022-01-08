package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Quiz {
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @JsonIgnore
    @ElementCollection
    private List<Integer> answer;

    @NotNull
    @Size(min = 2)
    @ElementCollection
    private List<String> options;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public void setUser(User user) {
        this.user = user;
    }


    @JsonIgnore
    public List<Integer> getAnswer() {
        return answer;
    }


    public Quiz() {

    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonProperty
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public Integer getId() {
        return id;
    }
}
