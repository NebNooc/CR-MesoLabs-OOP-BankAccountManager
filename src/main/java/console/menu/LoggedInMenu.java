package console.menu;

import console.ConsoleMock;
import console.Console;
import model.user.UserProfile;
import warehouse.AccountWarehouse;
import warehouse.UserProfileWarehouse;

public class LoggedInMenu {
    private UserProfileWarehouse userProfileWarehouse;
    private AccountWarehouse accountWarehouse;
    private UserProfile userProfile;

    public LoggedInMenu(UserProfileWarehouse userProfileWarehouse, AccountWarehouse accountWarehouse, UserProfile userProfile){
        this.userProfileWarehouse = userProfileWarehouse;
        this.accountWarehouse = accountWarehouse;
        this.userProfile = userProfile;
    }

    public void getLoggedInMenu() {
        Integer input = Console.getIntegerInput(
                "\nWhat would you like to do?\n\n" +
                        "1. Access Account\n" +
                        "2. Create Account\n" +
                        "3. Account Settings\n" +
                        "4. Back to Main Menu \n");
        loggedInMenuActions(input);
    }

    private void loggedInMenuActions(Integer input) {
        switch (input) {
            case 1:
                SelectAccountMenu selectAccountMenu = new SelectAccountMenu(userProfileWarehouse, accountWarehouse, userProfile);
                selectAccountMenu.getSelectAccountMenu();
                break;
            case 2:
                CreateAccountMenu createAccountMenu = new CreateAccountMenu(userProfileWarehouse, accountWarehouse, userProfile);
                createAccountMenu.getCreateAccountMenu();
                break;
            case 3:
                AccountSettings selectAccountSettings = new AccountSettings(userProfileWarehouse, accountWarehouse, userProfile);
                selectAccountSettings.getAccountSettingsMenu();
                break;
            case 4:
                Console.println("Bye!");
                System.exit(0);
                break;
            default:
                Console.println("\nInvalid selection. Please try again.");
                ConsoleMock consoleMock = new ConsoleMock(userProfileWarehouse, accountWarehouse);
                consoleMock.mainMenu();
        }

    }
}
