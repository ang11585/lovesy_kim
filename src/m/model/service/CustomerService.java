package m.model.service;

import m.common.ConnectionFactory;
import m.model.dao.CustomerDao;
import m.model.vo.Customer;
import myException.LibraryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService {
    private ConnectionFactory factory;

    public CustomerService() {
        factory = ConnectionFactory.getConnection();

    }

    public ArrayList<Customer> selectList() throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        ArrayList<Customer> list = new CustomerDao().selectList(conn);
        ConnectionFactory.close(conn);
        return list;
    }

    public ArrayList<Customer> selectNameSearch(String name) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        ArrayList<Customer> list = new CustomerDao().selectNameSearch(name,conn);
        ConnectionFactory.close(conn);
        return list;
    }

    public ArrayList<Customer> selectIdSearch(String id) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        ArrayList<Customer> list = new CustomerDao().selectIdSearch(id,conn);
        ConnectionFactory.close(conn);
        return list;
    }

    public int insertCustomer(Customer customer) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        int result = new CustomerDao().insertCustomer(customer,conn);
        if (result > 0) {
            factory.commit(conn);
        } else {
            factory.rollback(conn);
        }ConnectionFactory.close(conn);
        return result;
    }

    public int updateCustomer(Customer customer) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        int result = new CustomerDao().updateCustomer(customer,conn);
        if (result > 0) {
            factory.commit(conn);
        } else {
            factory.rollback(conn);
        }ConnectionFactory.close(conn);
        return result;
    }

    public int deleteCustomer(int userNo) throws SQLException, LibraryException {
        Connection conn = factory.createConnection();
        int result = new CustomerDao().deleteCustomer(userNo,conn);
        if (result > 0) {
            factory.commit(conn);
        } else {
            factory.rollback(conn);
        }ConnectionFactory.close(conn);
        return result;
    }
}
