package models;

import model.account.Account;
import model.account.SavingsAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SavingsAccountTests {
    Account savingsAccount;

    @Before
    public void testSetup() {
        Account savingsAccount = new SavingsAccount();
        this.savingsAccount = savingsAccount;
    }

    @Test
    public void getBalanceTest() {
        Double expected = 0.0;
        Double actual = savingsAccount.getAccountBalance();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStatus() {
        String expected = "Open";
        String actual = savingsAccount.getAccountStatus();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setStatus() {
        String expected = "Closed";
        savingsAccount.setAccountStatus("Closed");
        String actual = savingsAccount.getAccountStatus();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcNewBalanceTest() {
        setAccountBalance(100.0);

        Double expected = 105.0;
        Double actual = investmentAccount.calcNewBalance();

        Assert.assertEquals(expected, actual);
    }
}
