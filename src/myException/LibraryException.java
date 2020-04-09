package myException;

public class LibraryException extends Exception {
    public LibraryException() {
        super();
    }
    public LibraryException(String message){
        super(message);
    }
}
