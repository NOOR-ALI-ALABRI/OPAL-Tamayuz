package ATMProject;

import java.util.Scanner;

public class ATMProject {
    static Scanner scanner = new Scanner(System.in);
    static double balance = 1000.00;
    static int pin = 1234;

    static int depositCount = 0;
    static double depositTotal = 0.0;

    static int withdrawalCount = 0;
    static double withdrawalTotal = 0.0;

    // Bonus: highest successful deposit and withdrawal
    static double highestDeposit = 0.0;
    static double highestWithdrawal = 0.0;



    public static void main(String[] args) {
        
          
         boolean loggedIn = false;

         for (int i=0 ; i<3; i++){
           int enteredPin = readInt("Enter your PIN: ");

        if (enteredPin == pin) {
            loggedIn = true;
            break;
        } else {
            System.out.println("Wrong PIN. Attempts left: " + (2 - i));
        }
    }

    if (!loggedIn) {
        System.out.println("Card locked");
        return;
    }

    System.out.println("Welcome!");
       

    int choice;
    do{
        Menu();
        choice = readInt("Please enter a number between 1 and 7: ");

       switch (choice) {
        case 1:
            checkBalance();
            break;
        case 2:
            deposit();
            break;

        case 3  :
            withdraw();
            break;
        case 4:
            accountTier();
            break;
        case 5:
            savingsProjection();
            break;
        case 6  :
            receiptSummary();
            break;
        case 7 :
            changePin();
            break;
            case 0:
            System.out.println("Goodbye!");
            break;
        default:
            System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            break;
       }

    }while(choice != 0);

    }



static void Menu() {
        System.out.println("===== ATM Menu =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Account tier");
        System.out.println("5. Savings projection");
        System.out.println("6. Receipt summary");
        System.out.println("7. Change PIN");
        System.out.println("0. Exit");

    }

    static int readInt(String message) {  // Read an integer without crashing if the user enters letters
        System.out.print(message);

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // Clear the invalid input
            System.out.print(message);
        }
        return scanner.nextInt();
    }
    static double readDouble(String message) {  // Read a double without crashing if the user enters letters
        System.out.print(message);

        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear the invalid input
            System.out.print(message);
        }
        return scanner.nextDouble();
    }



    //checkBalance() 
    static void checkBalance() {
       System.out.println("Your balance is: " + balance);
    }

    //deposit() 
  static void deposit() {
    double amount = readDouble("Enter the amount to deposit: ");

    if (amount <= 0) {
        System.out.println("Amount must be greater than 0.");

    } else if (amount > 5000) {
        System.out.println("Amount exceeds the maximum deposit limit of 5000.");

    } else {
        balance += amount;
        depositCount++;
        depositTotal += amount;
        System.out.println("Deposit successful!");
    }
}

    //withdraw() 
    static void withdraw() {
          double amount = readDouble("Enter withdrawal amount: ");
          if (amount <= 0) {
    System.out.println("Amount must be greater than 0.");
    return;
    } 
     if (amount % 10 != 0) {
        System.out.println("Withdrawal amount must be a multiple of 10.");
        return;
    }
    if (withdrawalTotal + amount > 1000) {
        System.out.println("Session withdrawal limit is 1000.");
        return;
    }
     double fee;
    if (amount <= 500) {
        fee = 2.00;
    } else {
        fee = 5.00;
    }

    if (balance < amount + fee) {
        System.out.println("Insufficient balance.");
        return;
    }

    balance -= amount + fee;
    withdrawalCount++;
    withdrawalTotal += amount;

    if (amount > highestWithdrawal) {
        highestWithdrawal = amount;
    }

    System.out.println("Withdrawal successful!");
    System.out.println("Fee: " + fee);
    System.out.println("Remaining balance: " + balance);





    }


    //accountTier() 
     static String accountTier() {
    if (balance < 1000) {
        return "Bronze";
    } else if (balance < 5000) {
        return "Silver";
    } else if (balance < 20000) {
        return "Gold";
    } else {
        return "Platinum";
    }
    }


    //savingsProjection() 
    static void savingsProjection() {
            int years = readInt("Enter number of years (1-10): ");

    if (years < 1 || years > 10) {
        System.out.println("Years must be between 1 and 10.");
        return;
    }
    double rate;

    String tier = accountTier();

    if (tier.equals("Bronze")) {
        rate = 0.01;
    } else if (tier.equals("Silver")) {
        rate = 0.02;
    } else if (tier.equals("Gold")) {
        rate = 0.03;
    } else {
        rate = 0.04;
    }

    double projectedBalance = balance;

    for (int i = 1; i <= years; i++) {
        projectedBalance += projectedBalance * rate;
    }

    System.out.println("Projected balance after " + years + " years: " + projectedBalance);
    }

    //receiptSummary() 
    static void receiptSummary() {
         System.out.println("===== Receipt Summary =====");
    System.out.println("Current balance: " + balance);
    System.out.println("Deposit count: " + depositCount);
    System.out.println("Total deposits: " + depositTotal);
    System.out.println("Withdrawal count: " + withdrawalCount);
    System.out.println("Total withdrawals: " + withdrawalTotal);
    System.out.println("Highest deposit: " + highestDeposit);
    System.out.println("Highest withdrawal: " + highestWithdrawal);
    }

    //changePin() 
   static void changePin() {
  int oldPin = readInt("Enter current PIN: ");

    if (oldPin != pin) {
        System.out.println("Incorrect PIN.");
        return;
    }

    int newPin = readInt("Enter new 4-digit PIN: ");
if (newPin < 1000 || newPin > 9999) {
        System.out.println("PIN must be 4 digits.");
        return;
    }

    int confirmPin = readInt("Confirm new PIN: ");

    if (newPin != confirmPin) {
        System.out.println("PINs do not match.");
        return;
    }

    pin = newPin;
    System.out.println("PIN changed successfully!");


    }








}