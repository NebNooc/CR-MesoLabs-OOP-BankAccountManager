package service;

import console.Console;
import model.account.Account;
import model.account.CheckingAccount;
import model.account.SavingsAccount;
import model.user.UserProfile;
import warehouse.AccountWarehouse;
import java.util.ArrayList;
import java.util.List;

public class AccountServices {
    private AccountWarehouse accountWarehouse;
    public AccountServices(AccountWarehouse accountWarehouse){
        this.accountWarehouse = accountWarehouse;
    }

    public Integer getAccountNumber(Account currentAccount) {
        return currentAccount.getAccountNumber();
    }

    public Double getAccountBalance(Account currentAccount) {
        return currentAccount.getAccountBalance();
    }

    public Double withdraw(Account currentAccount, Double withdrawAmt) {
        Double currentBalance = currentAccount.getAccountBalance();
        Double newBalance = currentBalance - withdrawAmt;
        currentAccount.setAccountBalance(newBalance);
        return newBalance;
    }

    public Double deposit(Account currentAccount, Double depositAmt) {
        Double currentBalance = currentAccount.getAccountBalance();
        Double newBalance = currentBalance + depositAmt;
        currentAccount.setAccountBalance(newBalance);
        return newBalance;
    }

    public void transferBetweenAccounts(Account sourceAccount, Account targetAccount, Double transferAmt) {
        withdraw(sourceAccount, transferAmt);
        deposit(targetAccount, transferAmt);
    }

    public List<Account> getAllAccountsForAUser(UserProfile userProfile){
        List<Integer> allAccountNumbers = userProfile.getAccountNumberList();
        List<Account> allAccountsForGivenUser = new ArrayList<Account>();
        for(Integer accountNumber : allAccountNumbers){
            Account account = accountWarehouse.getAccountByAccountNumber(accountNumber);
            allAccountsForGivenUser.add(account);
        }
        return allAccountsForGivenUser;
    }

    public void getAccountSummary(List<Account> accountList) {
        for (Account account : accountList) {
            Console.print(getAccountType(account) + " Account Number: " + getAccountNumber(account) + " Account Balance: " + getAccountBalance(account) + "\n");
        }
    }

    public void printAccountNumbers(List<Account> accountList) {
        for (Account account : accountList) {
            Console.print(getAccountType(account) + " Account Number: " + getAccountNumber(account) + "\n");
        }
    }

    public String getAccountType(Account account){
        if (account instanceof CheckingAccount){
            return "Checking Account";
        } else if (account instanceof SavingsAccount) {
            return "Savings Account";
        } else return "Investment Account";
    }

}
