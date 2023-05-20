package bankingapplication2;

public class SavingAccount implements Account {
    
    private int accountNumber;
    private String accountName;
    private double balance;
    private final String accountType = "SavingAccount";
    
    public SavingAccount(int accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Can't withdraw, Not enough money.");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getAccountName() {
        return accountName;
    }
    
}
