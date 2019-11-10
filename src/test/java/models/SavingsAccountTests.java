package models;

import model.account.SavingsAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SavingsAccountTests {

    private SavingsAccount savingsAccount;

    @Before
    public void init() {
        savingsAccount  = new SavingsAccount();
    }

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

//    @Test
//    public void getStatus() {
//        String expected = "Open";
//        String actual = savingsAccount.getSatus();
//
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void setStatus() {
//        String expected = "Closed";
//        savingsAccount.setStatus("Closed");
//        String actual = savingsAccount.getStatus();
//
//        Assert.assertEquals(expected, actual);
//    }
}
