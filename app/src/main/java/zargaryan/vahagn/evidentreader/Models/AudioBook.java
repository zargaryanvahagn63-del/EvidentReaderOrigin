package zargaryan.vahagn.evidentreader.Models;

public class AudioBook extends Book {
    int length;
    AudioBook() {}
    AudioBook(String name, String author, int views, int length) {
        super(name, author, views);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
