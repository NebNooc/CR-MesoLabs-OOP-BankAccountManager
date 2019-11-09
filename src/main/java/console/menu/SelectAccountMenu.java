package console.menu;

import model.account.Account;
import console.Console;
import model.user.UserProfile;
import service.AccountServices;
import warehouse.AccountWarehouse;
import warehouse.UserProfileWarehouse;
import java.util.List;

public class SelectAccountMenu {
    private UserProfileWarehouse userProfileWarehouse;
    private AccountWarehouse accountWarehouse;
    private UserProfile userProfile;
    private AccountServices accountServices;

    public SelectAccountMenu(UserProfileWarehouse userProfileWarehouse, AccountWarehouse accountWarehouse, UserProfile userProfile) {
        this.userProfileWarehouse = userProfileWarehouse;
        this.accountWarehouse = accountWarehouse;
        this.userProfile = userProfile;
        accountServices = new AccountServices(accountWarehouse);
    }


    public void getSelectAccountMenu() {
        List<Account> accountList = accountServices.getAllAccountsForAUser(userProfile);
        accountServices.getAccountSummary(accountList);
        Integer input = Console.getIntegerInput("\nEnter the Account Number for the account you would like to access.\n");
        selectAccountMenuActions(input);
    }

    public void selectAccountMenuActions(Integer accountNumber) {
        Account selectedAccount = accountWarehouse.getAccountByAccountNumber(accountNumber);
        AccountActionMenu accountActionMenu = new AccountActionMenu(userProfileWarehouse, accountWarehouse, selectedAccount, userProfile);
        accountActionMenu.getAccountActionMenu();
    }
}