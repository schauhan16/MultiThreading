package com.shailendra.deadlock;

public class Account {

    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public static void transfer(Account fromAccount, Account toAccount, int amount) {
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    public int getBalance() {
      return balance;
    }

    public void setBalance(int balance) {
      this.balance = balance;
    }
}