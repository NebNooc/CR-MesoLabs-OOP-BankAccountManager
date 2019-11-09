package console.menu;

import console.ConsoleMock;
import model.account.CheckingAccount;
import console.Console;
import model.account.InvestmentAccount;
import model.account.SavingsAccount;
import model.user.UserProfile;
import warehouse.AccountWarehouse;
import warehouse.UserProfileWarehouse;

public class CreateAccountMenu {
    private UserProfileWarehouse userProfileWarehouse;
    private AccountWarehouse accountWarehouse;
    private UserProfile userProfile;

    public CreateAccountMenu(UserProfileWarehouse userProfileWarehouse, AccountWarehouse accountWarehouse, UserProfile userProfile){
        this.userProfileWarehouse = userProfileWarehouse;
        this.accountWarehouse = accountWarehouse;
        this.userProfile = userProfile;
    }

    public void getCreateAccountMenu() {
        Integer input = Console.getIntegerInput(
                "\nWhich account would you like to create?.\n\n" +
                        "1. Checking Account\n" +
                        "2. Savings Account\n" +
                        "3. Investment Account\n" +
                        "4. Back to Previous Menu \n" +
                        "5. Back to Main Menu \n");

        createAccountMenuActions(input);
    }

    private void createAccountMenuActions(Integer input) {
        switch (input) {
            case 1:
                CheckingAccount checkingAccount = new CheckingAccount();
                Integer accountNumber = checkingAccount.getAccountNumber();
                userProfile.addAccountNumberToList(accountNumber);
                accountWarehouse.add(checkingAccount);
                Console.println("Your new checking account's number is: " + accountNumber);
                getCreateAccountMenu();
                break;
            case 2:
                SavingsAccount savingsAccount = new SavingsAccount();
                accountNumber = savingsAccount.getAccountNumber();
                userProfile.addAccountNumberToList(accountNumber);
                accountWarehouse.add(savingsAccount);
                Console.println("Your new savings account's number is: " + accountNumber);
                getCreateAccountMenu();
                break;
            case 3:
                InvestmentAccount investmentAccount = new InvestmentAccount();
                accountNumber = investmentAccount.getAccountNumber();
                userProfile.addAccountNumberToList(accountNumber);
                accountWarehouse.add(investmentAccount);
                Console.println("Your new investment account's number is: " + accountNumber);
                getCreateAccountMenu();
                break;
            case 4:
                LoggedInMenu loggedInMenu = new LoggedInMenu(userProfileWarehouse, accountWarehouse, userProfile);
                loggedInMenu.getLoggedInMenu();
                break;
            case 5:
                ConsoleMock consoleMock = new ConsoleMock(userProfileWarehouse, accountWarehouse);
                consoleMock.mainMenu();
                break;
            default:
                Console.println("\nInvalid selection. Please try again.");
        }

    }
}



