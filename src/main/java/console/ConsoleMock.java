package console;

import model.user.UserProfile;
import console.menu.LoggedInMenu;
import warehouse.AccountWarehouse;
import warehouse.UserProfileWarehouse;

public class ConsoleMock {
    private UserProfileWarehouse userProfileWarehouse;
    private AccountWarehouse accountWarehouse;

    public ConsoleMock(UserProfileWarehouse userProfileWarehouse, AccountWarehouse accountWarehouse){
        this.userProfileWarehouse = userProfileWarehouse;
        this.accountWarehouse = accountWarehouse;

    }

    public void mainMenu() {
        Integer input = Console.getIntegerInput(
                "\nWelcome to the Bank of Kane ATM. Please select an option.\n\n" +
                        "1. Login\n" +
                        "2. Register\n" +
                        "3. Exit \n");

        mainMenuActions(input);
    }

    private void mainMenuActions(Integer input) {
        switch (input) {
            case 1:
                String userName = promptForUserName();
                String password = promptForPassword();
                UserProfile selectedProfile = authenticate(userName, password);
                LoggedInMenu loggedInMenu = new LoggedInMenu(userProfileWarehouse, accountWarehouse, selectedProfile);
                loggedInMenu.getLoggedInMenu();
                break;
            case 2:
                createProfile();
                mainMenu();
                break;
            case 3:
                Console.println("Bye!");
                System.exit(0);
                break;
            default:
                Console.println("\nInvalid selection. Please try again.");
                mainMenu();
        }

    }

    private String promptForUserName() {
        String userName = Console.getStringInput("What is your username?");
        return userName;
    }

    private String promptForPassword() {
        String password = Console.getStringInput("What is your password?");
        return password;
    }

    public void createProfile() {
        String firstName = Console.getStringInput("\nWhat is your first name?\n");
        String lastName = Console.getStringInput("\nWhat is your last name?\n");
        String userName = Console.getStringInput("\nWhat is your desired user name\n");
        String password = Console.getStringInput("\nWhat is your password\n");
        UserProfile newUser = new UserProfile(firstName, lastName, userName, password);
        userProfileWarehouse.add(newUser);
    }

   public UserProfile authenticate(String userName, String password) {
        UserProfile selectedProfile = userProfileWarehouse.getUserProfileByUserName(userName);
        if (selectedProfile != null && userProfileWarehouse.validatePassword(selectedProfile,password) ) {
            Console.println("Logged in successfully.");
            return selectedProfile;
        } else {
            Console.println("Invalid username or password. Please try again.");
            mainMenu();
        } return null;
    }
}