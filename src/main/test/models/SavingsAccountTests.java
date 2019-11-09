package models;

import model.account.SavingsAccount;
import org.junit.Assert;
import org.junit.Test;

public class SavingsAccountTests {

    SavingsAccount savingsAccount = new SavingsAccount();

    @Test
    public void generateAccountNumberTest() {
        System.out.println(savingsAccount.getAccountNumber());

    }

    @Test
    public void getBalanceTest() {
        Double expected = 0.0;
        Double actual = savingsAccount.getAccountBalance();

        Assert.assertEquals(expected, actual);
    }

}
