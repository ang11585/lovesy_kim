package m.model.vo;

public class Book {
    private int bookNum;
    private String bookName;
    private String bookWriter;
    private int bookiPrice;
    private String publisher;
    private String genre;

    public Book() {}

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public int getBookiPrice() {
        return bookiPrice;
    }

    public void setBookiPrice(int bookiPrice) {
        this.bookiPrice = bookiPrice;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return

                this.bookNum +", "  +
                this.bookName+", "  +
                this.bookWriter+", "  +
                this.bookiPrice+", "  +
                this.publisher+", "  +
                this.genre;
    }
}
