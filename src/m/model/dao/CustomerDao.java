package m.model.dao;

import m.common.ConnectionFactory;
import m.model.vo.Book;
import m.model.vo.Customer;
import myException.LibraryException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CustomerDao {
    public ArrayList<Customer> list;
    private Properties prop;

    public CustomerDao() {
        prop = new Properties();
        try {
            prop.load(new FileReader("resources/cQuery.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> selectList(Connection conn) throws LibraryException {
        ArrayList<Customer> list = null;
        Statement stmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectList");


        try {
            System.out.println("conn :" + conn);

            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);
            list = new ArrayList<Customer>();

            while (rset.next()) {
                Customer customer = new Customer();
                customer.setUserNo(rset.getInt("user_no"));
                customer.setUserId(rset.getString("user_id"));
                customer.setUserName(rset.getString("user_name"));
                customer.setUserAge(rset.getInt("user_age"));
                customer.setAddr(rset.getString("addr"));
                customer.setGender(rset.getString("gender"));
                customer.setEnrollDate(rset.getDate("enroll_date"));

                list.add(customer);
            }

        } catch (SQLException e) {
            throw new LibraryException(
                    "selectList() 메소드 처리불가 ->" + e.getMessage());
        } finally {
            ConnectionFactory.close(rset);
            ConnectionFactory.close(stmt);

        }
        return list;

    }

    public ArrayList<Customer> selectNameSearch(String name, Connection conn) throws LibraryException {
        ArrayList<Customer> list = null;
        Statement stmt = null;
        ResultSet rset = null;
        PreparedStatement pstmt = null;

        String query = prop.getProperty("selectNameSearch");

        //String query = "select * from customer where user_name = " + "'" + name + "'";

        try {
            System.out.println("conn :" + conn);
            //stmt = conn.createStatement();
           pstmt=conn.prepareStatement(query);
           pstmt.setString(1,name);
            rset = pstmt.executeQuery();

            list = new ArrayList<Customer>();

            while (rset.next()) {
                Customer customer = new Customer();
                customer.setUserNo(rset.getInt("user_no"));
                customer.setUserId(rset.getString("user_id"));
                customer.setUserName(rset.getString("user_name"));
                customer.setUserAge(rset.getInt("user_age"));
                customer.setAddr(rset.getString("addr"));
                customer.setGender(rset.getString("gender"));
                customer.setEnrollDate(rset.getDate("enroll_date"));

                list.add(customer);
            }


        } catch (SQLException e) {
           throw new LibraryException(
                   "selectNameSearch() 메소드 처리 불가 ->" + e.getMessage());
        } finally {
            ConnectionFactory.close(rset);
            ConnectionFactory.close(stmt);
        }
        return list;
    }

    public ArrayList<Customer> selectIdSearch(String id, Connection conn) throws LibraryException {
        ArrayList<Customer> list = null;
        Statement stmt = null;
        ResultSet rset = null;
        PreparedStatement pstmt = null;

        String query = prop.getProperty("selectIdSearch");
        //String query = "select * from customer where user_id = " + "'" + id + "'";

        try {
            System.out.println("conn :" + conn);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,id);
           // stmt = conn.createStatement();
            rset = pstmt.executeQuery();

            list = new ArrayList<Customer>();

            while (rset.next()) {
                Customer customer = new Customer();
                customer.setUserNo(rset.getInt("user_no"));
                customer.setUserId(rset.getString("user_id"));
                customer.setUserName(rset.getString("user_name"));
                customer.setUserAge(rset.getInt("user_age"));
                customer.setAddr(rset.getString("addr"));
                customer.setGender(rset.getString("gender"));
                customer.setEnrollDate(rset.getDate("enroll_date"));

                list.add(customer);
            }


        } catch (SQLException e) {
            throw new LibraryException(
                    "selectIdSearch() 메소드 처리 불가 ->" + e.getMessage());
        } finally {
            ConnectionFactory.close(rset);
            ConnectionFactory.close(stmt);
        }
        return list;
    }

    public int insertCustomer(Customer customer, Connection conn) throws LibraryException {
        int result = 0;
        //Statement stmt = null;
        PreparedStatement pstmt = null;

        String query = prop.getProperty("insertCustomer");

        /*String query = "insert into customer values( CUSTOMER_SEQ.NEXTVAL"
                + ","
                + "'" + customer.getUserId() + "',"
                + "'" + customer.getUserName() + "',"
                + customer.getUserAge() + ","
                + "'" + customer.getAddr() + "',"
                + "'" + customer.getGender() + "',"
                + "sysdate)";*/

        try {
            pstmt= conn.prepareStatement(query);
            pstmt.setString(1,customer.getUserId());
            pstmt.setString(2,customer.getUserName());
            pstmt.setInt(3,customer.getUserAge());
            pstmt.setString(4,customer.getAddr());
            pstmt.setString(5,customer.getGender());

            result = pstmt.executeUpdate();


        } catch (SQLException e) {
           throw new LibraryException(
                   "insert() 메소드 처리 불가 ->" + e.getMessage());
        } finally {
            ConnectionFactory.close(pstmt);


        } return result;


    }

    public int updateCustomer(Customer customer, Connection conn) throws LibraryException {
        int result = 0;
        PreparedStatement pstmt = null;
        //Statement stmt = null;

        String query = prop.getProperty("updateCustomer");


      /*  String query = "update customer set "
                + "user_name =" + "'"+
                customer.getUserName() + "',"
                + "user_age ='" + customer.getUserAge() + "',"
                + "addr ='" + customer.getAddr() + "'"
                + "where user_no = '" + customer.getUserNo() + "'";*/

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,customer.getUserName());
            pstmt.setInt(2,customer.getUserAge());
            pstmt.setString(3,customer.getAddr());
            pstmt.setInt(4,customer.getUserNo());
            //stmt = conn.createStatement();
            result = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new LibraryException(
                    "update() 메소드 처리 실패 ->" + e.getMessage());
        } finally {
            ConnectionFactory.close(pstmt);


        } return result;

    }

    public int deleteCustomer(int userNo, Connection conn) throws LibraryException {
        int result = 0;
        //Statement stmt = null;
        PreparedStatement pstmt = null;

        String query = prop.getProperty("deleteCustomer");

        //String query = "delete from customer where user_no= " + userNo;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,userNo);
            //stmt= conn.createStatement();
            result = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw  new LibraryException(
                    "delete() 메소드 수행 실패 ->" + e.getMessage());
        }finally {
           ConnectionFactory.close(pstmt);

        }
        return result;


    }
}

