package model.user;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private List<Integer> accountNumberList;

    public UserProfile(String firstName, String lastName, String userName, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
    accountNumberList = new ArrayList<Integer>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getUserName() {
        return this.userName;
    }


    public String getPassword() {
        return this.password;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public void setUserName(String newUserName) {
        this.userName = newUserName;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public List<Integer> getAccountNumberList() {
        return accountNumberList;
    }

    public void addAccountNumberToList(Integer accountNumber) {
        accountNumberList.add(accountNumber);
    }
}
