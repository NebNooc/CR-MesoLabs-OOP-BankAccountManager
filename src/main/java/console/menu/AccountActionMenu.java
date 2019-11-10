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
                        "5. Change Account Status \n"+
                        "6. Back to Previous Menu \n" +
                        "7. Back to Main Menu \n");
        accountActionMenuActions(input);
    }
    public void changeStatusMenu() {
        String accountStatus = accountServices.getAccountStatus(currentAccount);
        if (accountStatus == "Open") {
            Integer input = Console.getIntegerInput(
                    "\nWhat would you like to do?\n\n" +
                            "1. Close Account\n" +
                            "2. Freeze Account (OFAC)\n" +
                            "3. Back to Previous Menu \n");
            changeOpenAccountStatus(input);
        }
        else if (accountStatus == "OFAC") {
            Integer input = Console.getIntegerInput(
                    "\nWhat would you like to do?\n\n" +
                            "1. Close Account\n" +
                            "2. Unfreeze Account (OFAC)\n" +
                            "3. Back to Previous Menu \n");
            changeOFACAccountStatus(input);
        }
        else if (accountStatus == "Closed") {
            Console.println("\nThe Account is already closed. You cannot change its status at this point");
            getAccountActionMenu();
        }
    }

    private void changeOpenAccountStatus(Integer input) {
        switch (input) {
            case 1:
                closeAccount();
                getAccountActionMenu();
                break;
            case 2:
                freezeAccount();
                getAccountActionMenu();
                break;
            default:
                Console.println("\nInvalid selection. Please try again.");
                changeStatusMenu();
        }
    }

    private void changeOFACAccountStatus(Integer input) {
        switch (input) {
            case 1:
                closeAccount();
                getAccountActionMenu();
                break;
            case 2:
                unFreezeAccount();
                getAccountActionMenu();
                break;
            default:
                Console.println("\nInvalid selection. Please try again.");
                changeStatusMenu();
        }
    }

    private void closeAccount() {
        accountServices.setAccountStatus(currentAccount, "Closed");
        Console.println("\nThe Account is closed. Please note that you cannot reopen this Account again.");

    }

    private void freezeAccount() {
        String accountStatus = accountServices.getAccountStatus(currentAccount);
        if (accountStatus == "Open") {
            accountServices.setAccountStatus(currentAccount, "OFAC");
            Console.println("\nThe Account is set to Freeze. Please note that you can choose to unfreeze this Account again.");
        } else if (accountStatus == "Closed") {
            Console.println("\nThe Account is already closed. You cannot freeze the closed account");
        }
    }

    private void unFreezeAccount() {
        String accountStatus = accountServices.getAccountStatus(currentAccount);
        if (accountStatus == "OFAC") {
            accountServices.setAccountStatus(currentAccount, "Open");
            Console.println("\nThe Account is now set to Open. Please note that you can start using your Account.");
        }
        else if (accountStatus == "Closed") {
            Console.println("\nThe Account is already closed. You cannot freeze the closed account");
        }
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
                changeStatusMenu();
                break;

            case 6:
                LoggedInMenu loggedInMenu = new LoggedInMenu(userProfileWarehouse, accountWarehouse, userProfile);
                loggedInMenu.getLoggedInMenu();
                break;
            case 7:
                ConsoleMock consoleMock = new ConsoleMock(userProfileWarehouse, accountWarehouse);
                consoleMock.mainMenu();
                break;
            default:
                Console.println("\nInvalid selection. Please try again.");
                getAccountActionMenu();
        }
    }
}