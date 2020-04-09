package m.controller;

import m.model.dao.CustomerDao;
import m.model.service.CustomerService;
import m.model.vo.Book;
import m.model.vo.Customer;
import m.view.BookMenu;
import myException.LibraryException;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {
    public CustomerController() {
    }

    public void selectAll() {
        BookMenu menu = new BookMenu();
        ArrayList<Customer> list =null;

        try {
            try {
                list = new CustomerService().selectList();
                if (!list.isEmpty()) {
                    menu.displayCustomerList(list);
                } else
                    menu.displayError("전체 회원 조회 실패");
            } catch (LibraryException e) {
                System.out.println(e.getMessage());
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }



    }

    public void selectNameSearch(String name) {
        BookMenu menu = new BookMenu();
        ArrayList<Customer> list = null;


        try {
            list = new CustomerService().selectNameSearch(name);
            if (!list.isEmpty()) {
                menu.displayCustomerList(list);
            } else
                menu.displayError("해당" + name + "에 해당하는 회원정보가 없습니다.");


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }

        }

    public void selectIdSearch(String id) {
        BookMenu menu = new BookMenu();
        ArrayList<Customer> list = null;


        try {
            try {
                list = new CustomerService().selectIdSearch(id);
                if (!list.isEmpty()) {
                    menu.displayCustomerList(list);
                } else
                    menu.displayError("해당" + id + "에 해당하는 회원정보가 없습니다.");


            } catch (LibraryException e) {
                System.out.println(e.getMessage());

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

    public void insertCustomer(Customer customer) {
        BookMenu menu = new BookMenu();

        int result = 0;
        try {
            result = new CustomerService().insertCustomer(customer);
            if (result > 0) {
                menu.displaySuccess("회원 추가가 성공하였습니다.");
            } else {
                menu.displayError("회원 추가 실패!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }




    }

    public void updateCustomer(Customer customer) {
        BookMenu menu = new BookMenu();
        int result = 0;
        try {
            result = new CustomerService().updateCustomer(customer);
            if (result > 0) {
                menu.displaySuccess("회원 수정이 성공하였습니다.");
            } else {
                menu.displayError("회원 수정 실패!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (LibraryException e) {
            e.printStackTrace();
        }



    }

    public void deleteCustomer(int userNo) {
        BookMenu menu = new BookMenu();
        int result = 0;
        try {
            result = new CustomerService().deleteCustomer(userNo);
            if (result > 0) {
                menu.displaySuccess("해당 회원의 정보가 삭제되었습니다.");
            } else
                menu.displayError("회원 삭제 실패!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (LibraryException e) {
            e.printStackTrace();
        }



}


}
