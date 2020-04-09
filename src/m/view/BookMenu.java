package m.view;

import m.controller.BookConotroller;
import m.controller.CustomerController;
import m.controller.LibraryController;
import m.model.dao.LibraryDao;
import m.model.vo.Book;
import m.model.vo.Customer;
import m.model.vo.Library;

import java.util.ArrayList;
import java.util.Scanner;

public class BookMenu {
    private Scanner sc;
    private BookConotroller bConotroller;
    private CustomerController cController;
    private LibraryController lController;

    public BookMenu() {
        sc = new Scanner(System.in);
        bConotroller = new BookConotroller();
        cController = new CustomerController();
        lController = new LibraryController();
    }
    public void mainMenu() {
        int choice = 9;
        do {
            System.out.println("======메인메뉴======");
            System.out.println("1. 책관리");
            System.out.println("2. 회원관리");
            System.out.println("3. 대여관리");
            System.out.println("0. 종료");
            System.out.print("번호선택 : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bookSubMenu();
                    break;
                case 2:
                    customerSubMenu();
                    break;
                case 3:
                    librarySubMenu();
                    break;
                case 0:
                    System.out.print("정말 종료하시겠습니까?");
                    if (sc.next().charAt(0) == 'y') System.out.println("종료되었습니당.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다." +
                            " 다시 입력해주세요");
                    break;


            }
        }while(true);
    }

    public void bookSubMenu() {
        do {
            int choice = 9;
            System.out.println("====== 책 관리 서브메뉴 ======");
            System.out.println("1. 전체 책 조회");
            System.out.println("2. 책 코드로 조회");
            System.out.println("3. 책 추가하기");
            System.out.println("4. 책 삭제하기");
            System.out.println("5. 메인 메뉴로 이동");
            System.out.print("번호선택 : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bConotroller.selectAll();
                    break;
                case 2:
                    bConotroller.selectCodeSearch(this.inputBookCode());
                    break;
                case 3:
                    bConotroller.insertBook(this.inputBook());
                    break;
                case 4:
                    bConotroller.deleteBook(this.inputBookCode());
                    break;
                case 5:
                   return;
                default:
                    System.out.println("잘못 입력하셨습니다." +
                            " 다시 입력해주세요");
                    break;
            }
        }while(true);
    }

    public void customerSubMenu() {
        do {
            int choice = 9;
            System.out.println("====== 회원 관리 서브메뉴 ======");
            System.out.println("1. 회원 전체 조회");
            System.out.println("2. 회원 이름으로 조회");
            System.out.println("3. 회원 아이디로 조회");
            System.out.println("4. 회원 가입");
            System.out.println("5. 회원 정보 수정");
            System.out.println("6. 회원탈퇴");
            System.out.println("7. 메인 메뉴로 이동");
            System.out.print("번호선택 : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    cController.selectAll();
                    break;
                case 2:
                    cController.selectNameSearch(this.inputCustomerName());
                    break;
                case 3:
                    cController.selectIdSearch(this.inputCustomerId());
                    break;
                case 4:
                    cController.insertCustomer(this.inputCustomer());
                    break;
                case 5:
                    cController.updateCustomer(this.modifyCustomer());
                    break;
                case 6:
                    cController.deleteCustomer(this.inputCustomerNo());
                    break;
                case 7:
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다." +
                            " 다시 입력해주세요");
                    break;
            }
        }while(true);

    }

    public void librarySubMenu() {
        do {
            int choice = 9;
            System.out.println("====== 대여 관리 서브메뉴 ======");
            System.out.println("1. 대여 관리 전체 조회");
            System.out.println("2. 회원 아이디로 대여 조회");
            System.out.println("3. 책 이름으로 대여 조회");
            System.out.println("4. 대여 정보 추가");
            System.out.println("5. 메인 메뉴로 이동");
            System.out.print("번호선택 : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    lController.selectAll();
                    break;
                case 2:
                    lController.selectUserIdSearch(this.inputCustomerId());
                    break;
                case 3:
                    lController.selectBookNameSearch(this.inputBookName());
                    break;
                case 4:
                    lController.insertLibrary(this.inputLibrary());
                    break;
                case 5:
                   return;
                default:
                    System.out.println("잘못 입력하셨습니다." +
                            " 다시 입력해주세요");
                    break;
            }
        }while(true);

    }

    public void displayBookList(ArrayList<Book> list){
        System.out.println("조회된 책 전체 정보는 다음과 같습니다.");
        System.out.println("책코드 책이름 작가 책가격 출판사 장르 ");
        for(Book book : list){
            System.out.println(book.toString());
        }
    }

    public void displayCustomerList(ArrayList<Customer> list){
        System.out.println("조회된 회원 전체 정보는 다음과 같습니다.");
        System.out.println("회원번호 아이디 이름 나이 주소 성별 가입일자 ");
        for(Customer customer : list){
            System.out.println(customer.toString());
        }
    }

    public void displayLibraryList(ArrayList<Library> list){
        System.out.println("조회된 대여 전체 정보는 다음과 같습니다.");
        System.out.println("대여번호 회원아이디 회원이름 책이름 ");
        for(Library library : list){
            System.out.println(library.toString());
        }
    }

    public void displayError(String message){
        System.out.println("서비스 요청 처리 실패:" +
                message);
    }

    public int inputBookCode(){
        System.out.print("조회할 책 코드 번호 입력:");
       return sc.nextInt();

    }
    public String inputBookName() {
        System.out.println("조회할 책 이름 입력 : ");
        return sc.next();
    }
    public String inputCustomerName(){
        System.out.print("조회할 회원이름 입력:");
        return sc.next();

    }

    public String inputCustomerId(){
        System.out.print("조회할 회원아이디 입력:");
        return sc.next();

    }

    public int inputCustomerNo(){
        System.out.print("조회할 회원코드 입력:");
        return sc.nextInt();

    }


    public void displaySuccess(String message) {
        System.out.println("서비스 요청 결과 : " + message);
    }

    public Book inputBook() {
        Book book = new Book();
        System.out.print("책 이름 :");
        book.setBookName(sc.next());
        System.out.print("책 작가 :");
        book.setBookWriter(sc.next());
        System.out.print("책 가격 :");
        book.setBookiPrice(sc.nextInt());
        System.out.print("책 출판사 :");
        book.setPublisher(sc.next());
        System.out.print("책 장르 :");
        book.setGenre(sc.next());

        return book;

    }

    public Customer inputCustomer() {
        Customer customer = new Customer();
        System.out.print("회원 아이디 :");
        customer.setUserId(sc.next());
        System.out.print("회원 이름 :");
        customer.setUserName(sc.next());
        System.out.print("회원 나이 :");
        customer.setUserAge(sc.nextInt());
        sc.nextLine();
        System.out.print("회원 주소 :");
        customer.setAddr(sc.nextLine());

        System.out.print("회원 성별(M/F) :");
        customer.setGender(sc.next());

        return customer;
    }

    public Library inputLibrary() {
        LibraryDao libraryDao = new LibraryDao();
        Library library = new Library();
        System.out.println("책 이름 : ");
        library.setBookName(sc.next());
        System.out.print("회원 아이디 : ");
        library.setUserId(sc.next());



        return  library;

    }
    public Customer modifyCustomer() {
        Customer customer = new Customer();
        System.out.println("수정할 회원 정보를 입력하세요 :");

        System.out.println("수정할 회원의 회원코드");
        customer.setUserNo(sc.nextInt());

        System.out.print("수정할 회원의 이름 : ");
        customer.setUserName(sc.next());

        System.out.print("수정할 회원의 나이 : ");
        customer.setUserAge(sc.nextInt());

        System.out.print("수정할 회원의 주소 : ");
        customer.setAddr(sc.next());

        return customer;

    }


}

