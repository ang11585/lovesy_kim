package m.controller;

import m.model.dao.BookDao;
import m.model.service.BookService;
import m.model.vo.Book;
import m.view.BookMenu;
import myException.LibraryException;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookConotroller {
    public BookConotroller() {
    }

    public void selectAll() {
        BookMenu menu = new BookMenu();
        ArrayList<Book> list = null;

        try {
            list = new BookService().selectList();
            if (!list.isEmpty()){
                menu.displayBookList(list);
            }else{
                menu.displayError("전체 책 조회 실패");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (LibraryException e) {
            e.printStackTrace();
        }



    }

    public void selectCodeSearch(int bookCode){
        BookMenu menu = new BookMenu();
        ArrayList<Book> list = null;

        try {
            list = new BookService().selectCodeSearch(bookCode);
            if (!list.isEmpty()){
                menu.displayBookList(list);
            }else
                menu.displayError(
                        "해당" + bookCode + "에 일치하는 정보가 없습니다."
                );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (LibraryException e) {
            e.printStackTrace();
        }

    }

    public void insertBook(Book book) {
        BookMenu menu = new BookMenu();

        int result = 0;

        try {
            try {
                result = new BookService().insertBook(book);
                if (result > 0) {
                    menu.displaySuccess("책 추가가 성공하였습니다.");
                }else {
                    menu.displayError("책 추가 실패!");
                }
            } catch (LibraryException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void deleteBook(int bookCode) {
        BookMenu menu = new BookMenu();
        int result = 0;
        try {
            try {
                result = new BookService().deleteBook(bookCode);
                if (result > 0) {
                    menu.displaySuccess("해당 책의 정보가 삭제되었습니다.");
                }else
                    menu.displayError("책 삭제 실패!!");
            } catch (LibraryException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
}
