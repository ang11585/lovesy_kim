package m.controller;

import m.model.dao.LibraryDao;
import m.model.service.LibraryService;
import m.model.vo.Book;
import m.model.vo.Library;
import m.view.BookMenu;
import myException.LibraryException;

import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;

public class LibraryController {

    public LibraryController() {


    }

    public void selectAll() {
        BookMenu menu = new BookMenu();
        ArrayList<Library> list = null;

        try {
            try {
                list = new LibraryService().selectList();
                if (!list.isEmpty()) {
                    menu.displayLibraryList(list);
                } else
                    menu.displayError("전체 대여관리 조회 실패");
            } catch (LibraryException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void selectUserIdSearch(String userid){
        BookMenu menu = new BookMenu();
        ArrayList<Library> list  = null;
        try {
            list = new LibraryService().selectUserIdSearch(userid);
            if (!list.isEmpty()) {
                menu.displayLibraryList(list);
            } else
                menu.displayError("해당" + userid + "에 해당하는 대여정보가 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }


    }
    public void selectBookNameSearch(String bookName){
        BookMenu menu = new BookMenu();
        ArrayList<Library> list  = null;
        try {
            list= new LibraryService().selectBookNameSearch(bookName);
            if (!list.isEmpty()) {
                menu.displayLibraryList(list);
            } else
                menu.displayError("해당" + bookName + "에 해당하는 대여정보가 없습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }


    }

    public void insertLibrary(Library library) {
        BookMenu menu = new BookMenu();
        int result = 0;
        try {
            result = new LibraryService().insertLibrary(library);
            if (result > 0) {
                menu.displaySuccess("대여 정보 추가가 성공하였습니다.");
            } else {
                menu.displayError("대여 정보 추가 실패!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }

}
