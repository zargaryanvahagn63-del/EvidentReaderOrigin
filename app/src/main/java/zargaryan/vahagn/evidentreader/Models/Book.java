package zargaryan.vahagn.evidentreader.Models;

public class Book {
    String name;
    String author;
    int views;
    Book() {}
    Book(String name, String author, int views) {
        this.name = name;
        this.author = author;
        this.views = views;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
