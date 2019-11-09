package console.menu;

import console.Console;
import console.ConsoleMock;
import model.account.Account;
import model.user.UserProfile;
import service.AccountServices;
import warehouse.AccountWarehouse;
import warehouse.UserProfileWarehouse;

import java.util.List;

public class AccountActionMenu {
    private UserProfileWarehouse userProfileWarehouse;
    private AccountWarehouse accountWarehouse;
    private Account currentAccount;
    private UserProfile userProfile;
    private AccountServices accountServices;


    public AccountActionMenu(UserProfileWarehouse userProfileWarehouse, AccountWarehouse accountWarehouse, Account account, UserProfile userProfile){
        this.userProfileWarehouse = userProfileWarehouse;
        this.accountWarehouse = accountWarehouse;
        this.currentAccount = account;
        this.userProfile = userProfile;
        accountServices = new AccountServices(accountWarehouse);
    }

    public void getAccountActionMenu() {
        Integer input = Console.getIntegerInput(
                "\nWhat would you like to do?\n\n" +
                        "1. Withdraw\n" +
                        "2. Deposit\n" +
                        "3. Transfer money\n" +
                        "4. Check balance\n" +
                        "5. Back to Previous Menu \n" +
                        "6. Back to Main Menu \n");
        accountActionMenuActions(input);
    }

    private void accountActionMenuActions(Integer input) {
        switch (input) {
            case 1:
                Double withdrawAmt = Console.getDoubleInput("How much would you like to withdraw?");
                accountServices.withdraw(currentAccount, withdrawAmt);
                Console.println("Money withdrawn. Balance remaining: " + currentAccount.getAccountBalance());
                getAccountActionMenu();
                break;
            case 2:
                Double depositAmt = Console.getDoubleInput("How much would you like to deposit?");
                accountServices.deposit(currentAccount, depositAmt);
                Console.println("Money deposited. New balance: " + currentAccount.getAccountBalance());
                getAccountActionMenu();
                break;
            case 3:
                String targetUser = Console.getStringInput("Which user would you like to transfer to?");
                Double transferAmt = Console.getDoubleInput("How much would you like to transfer?");
                UserProfile targetUserProfile = userProfileWarehouse.getUserProfileByUserName(targetUser);
                List<Account> targetUsersAccounts = accountServices.getAllAccountsForAUser(targetUserProfile);
                Console.println("Here are the available accounts:\n");
                accountServices.printAccountNumbers(targetUsersAccounts);
                Integer targetAccountNumber = Console.getIntegerInput("Which account number do you want to transfer to?");
                Account targetAccount = accountWarehouse.getAccountByAccountNumber(targetAccountNumber);
                accountServices.transferBetweenAccounts(currentAccount, targetAccount, transferAmt);
                Console.println("Your new balance is: " + currentAccount.getAccountBalance());
                getAccountActionMenu();
                break;
            case 4:
                Console.println("Your account balance is: " + currentAccount.getAccountBalance());
                getAccountActionMenu();
                break;
            case 5:
                LoggedInMenu loggedInMenu = new LoggedInMenu(userProfileWarehouse, accountWarehouse, userProfile);
                loggedInMenu.getLoggedInMenu();
                break;
            case 6:
                ConsoleMock consoleMock = new ConsoleMock(userProfileWarehouse, accountWarehouse);
                consoleMock.mainMenu();
                break;
            default:
                Console.println("\nInvalid selection. Please try again.");
                getAccountActionMenu();
        }
    }
}