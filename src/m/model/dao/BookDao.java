package m.model.dao;

import m.common.ConnectionFactory;
import m.model.vo.Book;
import myException.LibraryException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class BookDao {
    private Properties prop;
    public ArrayList<Book> list;

    public BookDao() {
        prop = new Properties();
        try {
            prop.load(new FileReader("resources/bQuery.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Book> selectList(Connection conn) throws LibraryException {
        ArrayList<Book> list = null;
        Statement stmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectList");

        try {

            System.out.println("conn :" +  conn);
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);

            list = new ArrayList<Book>();
            while(rset.next()){
                Book book = new Book();
                book.setBookNum(rset.getInt("book_no"));
                book.setBookName(rset.getString("book_name"));
                book.setBookWriter(rset.getString("book_writer"));
                book.setBookiPrice(rset.getInt("book_price"));
                book.setPublisher(rset.getString("publisher"));
                book.setGenre(rset.getString("genre"));

                list.add(book);

            }

        } catch (SQLException e) {
            throw new LibraryException(
                    "selectList() 메소드 처리 불가 ->" + e.getMessage());
        }finally {

            ConnectionFactory.close(rset);
            ConnectionFactory.close(stmt);
        }
            return list;
        }



    public ArrayList<Book> selectCodeSearch(int bookCode, Connection conn) throws LibraryException {
        ArrayList<Book> list = null;

        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectCodeSearch");


        try {
            System.out.println("conn :" +  conn);
            pstmt=conn.prepareStatement(query);
            pstmt.setInt(1,bookCode);
            rset=pstmt.executeQuery();


            list = new ArrayList<Book>();
            while (rset.next()){
                Book book = new Book();
                book.setBookNum(rset.getInt("book_no"));
                book.setBookName(rset.getString("book_name"));
                book.setBookWriter(rset.getString("book_writer"));
                book.setBookiPrice(rset.getInt("book_price"));
                book.setPublisher(rset.getString("publisher"));
                book.setGenre(rset.getString("genre"));

                list.add(book);

            }
        } catch (SQLException e) {
            throw new LibraryException(
                    "selectCodeSearch() 메소드 처리 불가 ->" + e.getMessage());

        }finally {
            ConnectionFactory.close(rset);
            ConnectionFactory.close(stmt);

        }return list;


    }

    public int insertBook(Book book,Connection conn) throws LibraryException {
        int result = 0;
        Statement stmt = null;

        String query = prop.getProperty("insertBook");


        try {
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,book.getBookName());
            pstmt.setString(2,book.getBookWriter());
            pstmt.setInt(3,book.getBookiPrice());
            pstmt.setString(4,book.getPublisher());
            pstmt.setString(5,book.getGenre());
            result = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new LibraryException(
                    "insertBook() 메소드 처리 불가" + e.getMessage());
        }finally {

                ConnectionFactory.close(stmt);
        }
        return result;
    }

    public int deleteBook(int bookCode, Connection conn) throws LibraryException {
        int result = 0;
        PreparedStatement pstmt = null;

        String query = prop.getProperty("deleteBook");

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,bookCode);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
           throw new LibraryException(
                   "delete()메소드 처리 불가 ->" + e.getMessage());
        }finally {
           ConnectionFactory.close(pstmt);
        }
        return result;

    }
    }

