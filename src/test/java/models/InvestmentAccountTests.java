package models;

import model.account.InvestmentAccount;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class InvestmentAccountTests {

    InvestmentAccount investmentAccount = new InvestmentAccount();

    @Test
    public void generateAccountNumberTest() {
        System.out.println(investmentAccount.getAccountNumber());

    }

    @Test
    public void getBalanceTest() {
        Double expected = 0.0;
        Double actual = investmentAccount.getAccountBalance();

        Assert.assertEquals(expected, actual);
    }

}
