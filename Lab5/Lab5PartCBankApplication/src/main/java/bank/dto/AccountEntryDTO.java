package bank.dto;

import java.util.Date;

public class AccountEntryDTO {
    private Date date;
    private double amount;
    private String description;
    private String fromAccountNumber;
    private String fromPersonName;

    public AccountEntryDTO() {
    }

    public AccountEntryDTO(Date date, double amount, String description, String fromAccountNumber, String fromPersonName) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.fromAccountNumber = fromAccountNumber;
        this.fromPersonName = fromPersonName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getFromPersonName() {
        return fromPersonName;
    }

    public void setFromPersonName(String fromPersonName) {
        this.fromPersonName = fromPersonName;
    }

    @Override
    public String toString() {
        return "AccountEntryDTO{" +
                "date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", fromAccountNumber='" + fromAccountNumber + '\'' +
                ", fromPersonName='" + fromPersonName + '\'' +
                '}';
    }
}
