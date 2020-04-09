package m.model.service;

import m.common.ConnectionFactory;
import m.model.dao.LibraryDao;
import m.model.vo.Library;
import myException.LibraryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryService {
    private ConnectionFactory factory;

    public LibraryService() {
        factory = ConnectionFactory.getConnection();
    }

    public ArrayList<Library> selectList() throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        ArrayList<Library> list = new LibraryDao().selectList(conn);
        ConnectionFactory.close(conn);
        return list;
    }

    public ArrayList<Library> selectUserIdSearch(String userid) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        ArrayList<Library> list = new LibraryDao().selectUserIdSearch(userid,conn);
        ConnectionFactory.close(conn);
        return list;
    }

    public ArrayList<Library> selectBookNameSearch(String bookName) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        ArrayList<Library> list = new LibraryDao().selectBookNameSearch(bookName,conn);
        ConnectionFactory.close(conn);
        return list;
    }
    public int insertLibrary(Library library) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        String bookName = null;
        int result = new LibraryDao().insertLibrary(library,bookName,conn);
        if (result >0) {
            factory.commit(conn);
        }else {
            factory.rollback(conn);
        }
        ConnectionFactory.close(conn);
        return result;
    }
}
