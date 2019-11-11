package console.menu;

import console.Console;
import console.ConsoleMock;
import model.user.UserProfile;
import warehouse.AccountWarehouse;
import warehouse.UserProfileWarehouse;

public class AccountSettings {
    private UserProfileWarehouse userProfileWarehouse;
    private AccountWarehouse accountWarehouse;
    private UserProfile userProfile;

    public AccountSettings(UserProfileWarehouse userProfileWarehouse, AccountWarehouse accountWarehouse, UserProfile userProfile){
        this.userProfileWarehouse = userProfileWarehouse;
        this.accountWarehouse = accountWarehouse;
        this.userProfile = userProfile;
    }


    public void getAccountSettingsMenu() {
        Integer input = Console.getIntegerInput(
                "\nWhat would you like to do?\n\n" +
                        "1. Change First Name\n" +
                        "2. Change Last Name\n" +
                        "3. Change UserName\n" +
                        "4. Change Password\n" +
                        "5. Back to Previous Menu \n" +
                        "6. Back to Main Menu \n");
        accountSettingsActions(input);
    }
    private void accountSettingsActions(Integer input) {
    switch(input){
        case 1:
            Console.print("Your current First Name is: " + userProfile.getFirstName() + "\n");
            String newFirstName = Console.getStringInput("What is your desired first name?");
            userProfile.setFirstName(newFirstName);
            Console.print("Your new First Name is: " + userProfile.getFirstName());
            getAccountSettingsMenu();
            break;
        case 2:
            Console.print("Your current Last Name is: " + userProfile.getLastName() + "\n");
            String newLastName = Console.getStringInput("What is your desired last name?");
            userProfile.setLastName(newLastName);
            Console.print("Your new Last Name is: " + userProfile.getLastName());
            getAccountSettingsMenu();
            break;
        case 3:
            Console.print("Your current username is: " + userProfile.getUserName() + "\n");
            String newUserName = Console.getStringInput("What is your desired username?");
            userProfile.setUserName(newUserName);
            Console.print("Your new username is: " + userProfile.getUserName());
            getAccountSettingsMenu();
            break;
        case 4:
            Console.print("Your current password is: " + userProfile.getPassword() + "\n");
            String newPassword = Console.getStringInput("What is your desired password?");
            userProfile.setPassword(newPassword);
            Console.print("Your new password is: " + userProfile.getPassword());
            getAccountSettingsMenu();
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
            getAccountSettingsMenu();

    }
}

}
