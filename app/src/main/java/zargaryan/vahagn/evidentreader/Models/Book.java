package zargaryan.vahagn.evidentreader.Models;

import java.util.List;

public class Book {
    private static int counter = 0;
    private String name;
    private String author;
    private String filePath;
    private List<String> tags;
    private int id = counter++;
    public Book() {}
    public Book(String name, String author, String filePath, int id) {
        this.name = name;
        this.author = author;
        this.filePath = filePath;
        this.id = id;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
