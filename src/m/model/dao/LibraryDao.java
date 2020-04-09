package m.model.dao;

import m.common.ConnectionFactory;
import m.model.vo.Book;
import m.model.vo.Customer;
import m.model.vo.Library;
import m.model.dao.BookDao;
import myException.LibraryException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class LibraryDao {
    private Properties prop;
    ArrayList<Library> list;
    ArrayList<Book> bookArr;

    public LibraryDao() {
        prop = new Properties();
        try {
            prop.load(new FileReader("resources/lQuery.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Library> selectList(Connection conn) throws LibraryException {
        ArrayList<Library> list = null;
        Statement stmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectList");


        try {
            System.out.println("conn :" + conn);

            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);

            list = new ArrayList<Library>();
            while (rset.next()) {
                Library library = new Library();
                library.setLeaseNo(rset.getInt("lease_no"));
                library.setUserId(rset.getString("user_id"));
                library.setUserName(rset.getString("user_name"));
                library.setBookName(rset.getString("book_name"));
                list.add(library);
            }

        } catch (SQLException e) {
          throw new LibraryException(
                  "selectList() 메소드 실행 불가 ->" + e.getMessage());
        } finally {
            ConnectionFactory.close(rset);
            ConnectionFactory.close(stmt);

        }
        return list;

    }

    public ArrayList<Library> selectUserIdSearch(String userid,Connection conn) throws LibraryException {
        ArrayList<Library> list = null;
        Statement stmt = null;
        ResultSet rset = null;
        PreparedStatement pstmt = null;

        String query = prop.getProperty("selectUserIdSearch");



        /*String query = "select  l.lease_no, l.user_id, c.user_name, b.book_name from library l " +
                "left join book b on (b.book_no = l.book_no) " +
                "left join customer c on (c.user_id = l.user_id) " +
                "where l.user_id = " + "'" + userid + "'";
*/
        try {
            System.out.println("conn :" + conn);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,userid);
            //stmt = conn.createStatement();
            rset = pstmt.executeQuery();

            list = new ArrayList<Library>();

            while (rset.next()) {
                Library library = new Library();
                library.setLeaseNo(rset.getInt("lease_no"));
                library.setUserId(rset.getString("user_id"));
                library.setUserName(rset.getString("user_name"));
                library.setBookName(rset.getString("book_name"));

                list.add(library);
            }


        } catch (SQLException e) {
            throw new LibraryException(
                    "selectUserIdSearch() 메소드 수행 불가 ->" + e.getMessage());
        } finally {
            ConnectionFactory.close(rset);
            ConnectionFactory.close(pstmt);
        }
        return list;

    }

    public ArrayList<Library> selectBookNameSearch(String bookName, Connection conn) throws LibraryException {
        ArrayList<Library> list = null;

        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rset = null;
        ResultSet rset2 = null;

        String query = null;

        String query2 = prop.getProperty("selectBookNameSearch2");
        /*String query2 = "select book_no from library where " +
                "book_no = (select book_no from book where book_name ="
                + "'" + bookName + "'" + ")";*/

        try {
            System.out.println("conn :" + conn);
            pstmt = conn.prepareStatement(query2);
            pstmt.setString(1,bookName);
            rset = pstmt.executeQuery();
            /*stmt = conn.createStatement();
            rset = stmt.executeQuery(query2);*/
            if(rset.next()) {


                query = prop.getProperty("selectBookNameSearch")+ rset.getString("book_no");
            }

            pstmt = conn.prepareStatement(query);
            rset2 = pstmt.executeQuery();

            list = new ArrayList<Library>();

            while (rset2.next()) {
                Library library = new Library();
                library.setLeaseNo(rset2.getInt("lease_no"));
                library.setUserId(rset2.getString("user_id"));
                library.setUserName(rset2.getString("user_name"));
                library.setBookName(rset2.getString("book_name"));


                list.add(library);
            }


        } catch (SQLException e) {
           throw new LibraryException(
                   "selectBookNameSearch() 메소드 수행 불가 ->" + e.getMessage());
        } finally {
           ConnectionFactory.close(rset);
           ConnectionFactory.close(stmt);
        }
        return list;

    }
    

    public int insertLibrary(Library library,String bookName, Connection conn) throws LibraryException, SQLException {
        int result = 0;

        PreparedStatement pstmt = null;
        Statement stmt = null;

        String query = prop.getProperty("insertLibrary");




        /*String query = "insert into library values(LEASE_SEQ.NEXTVAL,(select book_no from book where book_name = " + "'" + library.getBookName() + "'" + "),"
                + "'" + library.getUserId() + "',"
                + "sysdate" + ","
                + "sysdate+7)";*/

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,library.getBookName());
            pstmt.setString(2,library.getUserId());
            //stmt = conn.createStatement();
            result = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new LibraryException(
                    "insertLibrary() 메소드 수행 불가 ->" + e.getMessage());
        } finally {
            ConnectionFactory.close(pstmt);


        } return result;

    }
}
