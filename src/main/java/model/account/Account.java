package model.account;

public abstract class Account {
    private Integer accountNumber;
    private Double accountBalance = 0.00;

    public Account(){
        this.accountNumber = generateAccountNumer();
    }


    public Integer getAccountNumber() {
        return this.accountNumber;
    }

    public Double getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Integer generateAccountNumer() {
        Integer max = 9999999;
        Integer min = 1000000;
        Integer range = max - min + 1;

        return (int)(Math.random() * range) + min;
        }
}
