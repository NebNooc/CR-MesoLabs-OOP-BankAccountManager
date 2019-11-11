package model.account;

import interfaces.InterestRate;

public class SavingsAccount extends Account implements InterestRate {

    public void calcNewBalance() {
        setAccountBalance(getAccountBalance() * (1 + (rate * 1)));
    }
}
