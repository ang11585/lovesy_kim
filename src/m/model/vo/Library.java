package m.model.vo;

import java.sql.Date;

public class Library {
    private int leaseNo;
    private int bookNo;
    private String userId;
    private String userName;
    private String bookName;
    private Date leaseDate;
    private Date returnDate;

    public Library() {}

    public int getLeaseNo() {
        return leaseNo;
    }

    public void setLeaseNo(int leaseNo) {
        this.leaseNo = leaseNo;
    }

    public int getBookNo() {
        return bookNo;
    }

    public void setBookNo(int bookNo) {
        this.bookNo = bookNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return
                this.leaseNo + ", " +
                this.userId + ", " +
                this.userName+ ", " +
                this.bookName;
    }

    public int setBookNo() {
        this.bookNo = bookNo;
        return bookNo;
    }
}
