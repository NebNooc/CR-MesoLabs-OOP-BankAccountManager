package warehouse;

import model.account.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountWarehouse {

    private volatile Map<Integer, Account> accounts = new HashMap<Integer, Account>();

    public Account getAccountByAccountNumber(Integer accountNumber){
        return accounts.get(accountNumber);
    }

    public void add(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }
}

