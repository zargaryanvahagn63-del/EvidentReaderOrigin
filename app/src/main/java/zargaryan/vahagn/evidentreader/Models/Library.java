package zargaryan.vahagn.evidentreader.Models;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<String> tags;

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public List<String> getTags() {
        return tags;
    }
    public String getTag(int index) {
        return tags.get(index);
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public void addTag(String tag) {
        tags.add(tag);
    }
    public Library() {
        books = new ArrayList<>();
    }
}
