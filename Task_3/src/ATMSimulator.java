import java.io.*;
import java.util.*;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getPin() { return pin; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        balance += amount;
        updateAccount();
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return false;
        }
        balance -= amount;
        updateAccount();
        return true;
    }

    public void updateAccount() {
        List<String> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(accountNumber)) {
                    accounts.add(accountNumber + "," + pin + "," + balance);
                } else {
                    accounts.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("accounts.txt"))) {
            for (String acc : accounts) {
                bw.write(acc);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    public static Account getAccount(String accountNumber, String pin) {
        try (BufferedReader br = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(accountNumber) && data[1].equals(pin)) {
                    return new Account(data[0], data[1], Double.parseDouble(data[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return null;
    }
}

public class ATMSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        Account userAccount = Account.getAccount(accountNumber, pin);

        if (userAccount == null) {
            System.out.println("Invalid credentials! Exiting...");
            return;
        }

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + userAccount.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    userAccount.deposit(depositAmount);
                    System.out.println("Amount Deposited Successfully!");
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (userAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal Successful!");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
