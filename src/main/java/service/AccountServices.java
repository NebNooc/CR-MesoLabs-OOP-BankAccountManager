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

    public String getAccountStatus(Account currentAccount) {
        return currentAccount.getAccountStatus();
    }

    public void setAccountStatus(Account currentAccount, String status) {
        currentAccount.setAccountStatus(status);
        //return currentAccount.getAccountStatus();
    }

    public Double withdraw(Account currentAccount, Double withdrawAmt) {
        Double currentBalance = currentAccount.getAccountBalance();
        Double newBalance;
        if(withdrawAmt <= currentBalance){
            newBalance = currentBalance - withdrawAmt;
            String historyMessage = withdrawAmt + " withdrawn.";
            writeToHistory(currentAccount, historyMessage);
        }
        else{
            Console.print("\nSorry, You have insufficient funds in your account, please lower your withdrawl amount.\n");
            newBalance  = currentBalance;
            String historyMessage = "Attempted overdraft.";
            writeToHistory(currentAccount, historyMessage);
        }
        currentAccount.setAccountBalance(newBalance);
        return newBalance;
    }

    public Double deposit(Account currentAccount, Double depositAmt) {
        Double currentBalance = currentAccount.getAccountBalance();
        Double newBalance = currentBalance + depositAmt;
        currentAccount.setAccountBalance(newBalance);
        String historyMessage = depositAmt + " deposited.";
        writeToHistory(currentAccount, historyMessage);
        return newBalance;
    }

    public void transferBetweenAccounts(Account sourceAccount, Account targetAccount, Double transferAmt) {
        Double currentBalance = sourceAccount.getAccountBalance();
        if(transferAmt <= currentBalance){
            withdraw(sourceAccount, transferAmt);
            deposit(targetAccount, transferAmt);
            String historyMessage = transferAmt + " transferred to " + targetAccount.getAccountNumber() + ".";
            writeToHistory(sourceAccount, historyMessage);
        } else{
            Console.print("\nThere are insufficient funds in the Transferring Account for this transfer.\n");
            String historyMessage = "Attempted transfer with insufficient funds.";
            writeToHistory(sourceAccount, historyMessage);
        }

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

    public String writeToHistory(Account account, String message) {
        account.addToHistory(message);
        return message;
    }

    public String printTransactionHistory(Account account) {
        ArrayList<String> history = account.getTransactionHistory();
        StringBuilder sb = new StringBuilder();
        for (String message : history) {
            sb.append(message + "\n");
        }
        return sb.toString();
    }
}
