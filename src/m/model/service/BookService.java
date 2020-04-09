package m.model.service;

import m.common.ConnectionFactory;
import m.model.dao.BookDao;
import m.model.vo.Book;
import myException.LibraryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookService {
    private ConnectionFactory factory;

    public BookService() {
        factory = ConnectionFactory.getConnection();
    }

    public ArrayList<Book> selectList() throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        ArrayList<Book> list = new BookDao().selectList(conn);
        ConnectionFactory.close(conn);
        return list;
    }

    public ArrayList<Book> selectCodeSearch(int bookCode) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        ArrayList<Book> list = new BookDao().selectCodeSearch(bookCode,conn);
        ConnectionFactory.close(conn);
        return list;
    }

    public int insertBook(Book book) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        int result = new BookDao().insertBook(book,conn);
        if (result>0) {
            factory.commit(conn);
        }else{
            factory.rollback(conn);
        }ConnectionFactory.close(conn);
        return result;
    }

    public int deleteBook(int bookCode) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        int result = new BookDao().deleteBook(bookCode, conn);
        if (result > 0) {
            factory.commit(conn);
        } else {
            factory.rollback(conn);
        }
        ConnectionFactory.close(conn);
        return result;
    }

}
