
import warehouse.AccountWarehouse;
import warehouse.UserProfileWarehouse;
import console.ConsoleMock;

/**
 * Created by iyasuwatts on 10/17/17.
 */
public class Main {


    public static void main(String[] args){
        UserProfileWarehouse userProfileWarehouse = new UserProfileWarehouse();
        AccountWarehouse accountWarehouse = new AccountWarehouse();
        ConsoleMock consoleMock = new ConsoleMock(userProfileWarehouse, accountWarehouse);
        consoleMock.mainMenu();
    }
}
