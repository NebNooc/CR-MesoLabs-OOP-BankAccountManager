package models;

import model.account.CheckingAccount;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckingAccountTest {

CheckingAccount checkingAccount = new CheckingAccount();

    @Test
    public void generateAccountNumberTest() {
        System.out.println(checkingAccount.getAccountNumber());

    }

    @Test
    public void getBalanceTest() {
        Double expected = 0.0;
        Double actual = checkingAccount.getAccountBalance();

        Assert.assertEquals(expected, actual);
    }




}