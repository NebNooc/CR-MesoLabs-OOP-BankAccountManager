package model.account;

import model.user.UserProfile;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Account {
    private Integer accountNumber;
    private Double accountBalance = 0.00;
    private String accountStatus = "Open";
    private UserProfile accountOwner;
    Map<Date, String> transactionHistory;


    public Account(){
        this.accountNumber = generateAccountNumer();
        this.transactionHistory = new LinkedHashMap<Date, String>();
    }

    public Account(UserProfile accountOwner){
        this.accountNumber = generateAccountNumer();
        this.accountOwner = accountOwner;
        this.transactionHistory = new LinkedHashMap<Date, String>();
    }


    public Integer getAccountNumber() {
        return this.accountNumber;
    }

    public Double getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Integer generateAccountNumer() {
        Integer max = 9999999;
        Integer min = 1000000;
        Integer range = max - min + 1;

        return (int)(Math.random() * range) + min;
        }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public UserProfile getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(UserProfile userProfile) {
        this.accountOwner = accountOwner;
    }

}
