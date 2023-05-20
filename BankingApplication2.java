package bankingapplication2;

import java.util.Random;
import java.util.Scanner;

public class BankingApplication2 {

    public static void main(String[] args) {
        
        Bank bank = new Bank("Kbank");
        Account account = null;
        int option, accNumber;
        String accName, accType;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Main Menu --------");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Open New Account");
            System.out.println("3. Close Existing Account");
            System.out.println("4. Desposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Exit");
        
            System.out.print("Enter your choice: ");
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            try {
                switch (option) {
                    case 1 -> bank.listAccount();
                    case 2 -> {
                        accNumber = genAccountNumber();
                        double balance;

                        System.out.print("Enter Account Name: ");
                        accName = sc.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        balance = sc.nextDouble();
                        sc.nextLine();
                        
                        System.out.println("Enter Account Type");
                        System.out.println("\ts -> Saving Account");
                        System.out.println("\tc -> Current Account");
                        System.out.print(": ");
                        accType = sc.nextLine();
                        
                        do {
                            if (accType.toLowerCase().equals("s")) {
                                account = new SavingAccount(accNumber, accName, balance);
                                break;
                            } else if (accType.toLowerCase().equals("c")) {
                                account = new CurrentAccount(accNumber, accName, balance);
                                break;
                            } else {
                                System.out.println("Please Enter Account Type (s,c only)");
                                System.out.println("\ts -> Saving Account");
                                System.out.println("\tc -> Current Account");
                                System.out.print(": ");
                                accType = sc.nextLine();
                            }
                        } while (true);
                        
                        if (bank.openAccount(account)) {
                            System.out.println("\nAccount Creation Successful.");
                            System.out.println("\tAccount Number: " + account.getAccountNumber() + " (" + account.getAccountType() + ")");
                            System.out.println("\tName: " + account.getAccountName());
                            System.out.println("\tInitial Balance: " + account.getBalance()+ "\n");
                        } else {
                            System.out.println("Account Creation Failed.");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Account Number: ");
                        accNumber = sc.nextInt();
                        sc.nextLine();
                        account = bank.getAccount(accNumber);
                        bank.closeAccount(account);
                    }
                    case 4 -> {
                        double amount;
                        System.out.print("Enter Account Number: ");
                        accNumber = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter amount for desposit: ");
                        amount = sc.nextDouble();
                        account = bank.getAccount(accNumber);
                        bank.despositMoney(account, amount);
                        System.out.println();
                    }
                    case 5 -> {
                        double amount;
                        System.out.print("Enter Account Number: ");
                        accNumber = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter amount for withdraw: ");
                        amount = sc.nextDouble();
                        account = bank.getAccount(accNumber);
                        bank.withdrawMoney(account, amount);
                        System.out.println();
                    }
                    case 6 -> {}
                    default -> throw new AssertionError();
                }
            } catch (AssertionError ex) {
                System.out.println("Please Enter Only 1-6");
            }
            
            
        } while (option != 6);
    }
    
    public static int genAccountNumber() {
        Random rand = new Random();
        int accNumber = 100000 + rand.nextInt(900000);
        return accNumber;
    }
    
}
