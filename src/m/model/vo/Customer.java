package m.model.vo;

import java.sql.Date;

public class Customer {
    private int userNo;
    private String userId;
    private String userName;
    private int userAge;
    private String Addr;
    private String gender;
    private Date enrollDate;

    public Customer() {}

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    @Override
    public String toString() {
        return

                this.userNo + "," +
                this.userId + "," +
                this.userName + "," +
                this.userAge + "," +
                this.Addr+ "," +
                this.gender + "," +
                this.enrollDate;
    }
}
