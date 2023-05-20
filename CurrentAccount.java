package bankingapplication2;

public class CurrentAccount implements Account {
    private int accountNumber;
    private String accountName;
    private double balance;
    private double minimum;
    private final String accountType = "CurrentAccount";
    
    public CurrentAccount(int accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }
    
    public double getMinimum() {
        return minimum;
    }
    
    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if ((balance - amount) > minimum) {
            balance -= amount;
        } else {
            System.out.println("Can't withdraw, Not reach minimum.");
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
