package zargaryan.vahagn.evidentreader.Models;

public class AudioBook extends Book {
    private int length;
    AudioBook() {}
    public AudioBook(String name, String author, String filePath, int length, int id) {
        super(name, author, filePath, id);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}