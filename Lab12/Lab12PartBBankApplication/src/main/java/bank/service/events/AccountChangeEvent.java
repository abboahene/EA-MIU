package bank.service.events;

import jakarta.persistence.Entity;

@Entity
public class AccountChangeEvent {
    private String message = "";
    private long accountNumber;
    private double amount;

    public AccountChangeEvent(){}

    public AccountChangeEvent(String message, long accountNumber, double amount) {
        this.message = message;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
