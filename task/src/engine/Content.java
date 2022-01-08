package engine;

import java.util.List;

public class Content {
    public int totalPages;
    public int totalElements;
    public boolean last, first, empty;
    public String[] sort = new String[]{};
    public String[] pageable = new String[]{};
    public List<Quiz> content;
    public int number;
    public int numberOfElements;
    public int size;

    public Content() {

    }

    public Content(int totalPages, int totalElements, boolean last, boolean first, boolean empty, List<Quiz> content, int number, int numberOfElements, int size) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.last = last;
        this.first = first;
        this.empty = empty;

        this.content = content;
        this.number = number;
        this.numberOfElements = numberOfElements;
        this.size = size;
    }
}
