package bankingapplication2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {

    private final String name;
    
    public Bank(String name) {
        this.name = name;
    }
    
    public void listAccount() {
        try {
            Connection connection = BankingConnection.connect();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM accounts";
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            
            System.out.println("\nList of Accounts --------");
            for (int i = 1; i <= rsMetaData.getColumnCount(); i++)
                System.out.printf("%-12s\t", rsMetaData.getColumnName(i));
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                    System.out.printf("%-12s\t", resultSet.getObject(i));
                }
                System.out.println();
            }
            System.out.println();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(BankingApplication2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean openAccount(Account account) {
        try { 
            Connection connection = BankingConnection.connect();
            String sql = "INSERT INTO accounts(number, name, balance, accType) "
                    + "VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getAccountName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4, account.getAccountType());
            
            preparedStatement.executeUpdate();
            connection.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(BankingApplication2.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean closeAccount(Account account) {
        try {
            Connection connection = BankingConnection.connect();
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM accounts WHERE number = " + account.getAccountNumber();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void despositMoney(Account account, double amount) {
        account.deposit(amount);
        
        try {
            Connection connection = BankingConnection.connect();
            String sql = "UPDATE accounts SET balance = ? WHERE number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void withdrawMoney(Account account, double amount) {
        account.withdraw(amount);
        
        try {
            Connection connection = BankingConnection.connect();
            String sql = "UPDATE accounts SET balance = ? WHERE number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Account getAccount(int number) {
        
        Account account = null;
        
        try {
            Connection connection = BankingConnection.connect();
            String sql = "SELECT * FROM accounts WHERE number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            resultSet.next();
            
            if (resultSet.getString(4).equals("SavingAccount")) {
                account = new SavingAccount(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));
            } else if (resultSet.getString(4).equals("CurrentAccount")) {
                account = new CurrentAccount(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));
            } 
               
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
    
}
