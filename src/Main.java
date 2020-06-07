import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean quit = false;
    private static Bank bank = new Bank("cibc");

    public static void main(String[] args) {
        printOptions();
        while (!quit){
            System.out.println("\n1 - Print Available Options");
            int opt = scanner.nextInt();scanner.nextLine();
            String customerName;
            String senderName;
            String recipientName;
            String s_branchName;
            String r_branchName;
            String branchName;
            double initTransaction;
            double transaction;
            switch (opt){
                case 0:
                    System.out.println("\nExiting Bank...");
                    quit=true;
                    break;
                case 1:
                    printOptions();
                    break;
                case 2:
                    System.out.println("Enter name of customer");
                    customerName = scanner.nextLine();

                    System.out.println("Enter deposit amount");
                    initTransaction = scanner.nextDouble();scanner.nextLine();

                    System.out.println("Enter name of branch");
                    branchName = scanner.nextLine();

                    if (bank.addCustomer(branchName, customerName, initTransaction)){
                        System.out.println(customerName+" added to branch "+branchName);
                    }
                    else{
                        System.out.println("Error: Something went wrong. Please check inputs...");
                    }
                    break;
                case 3:
                    System.out.println("Enter name of sender");
                    senderName = scanner.nextLine();

                    System.out.println("Enter name of recipient");
                    recipientName = scanner.nextLine();

                    System.out.println("Enter transaction amount");
                    transaction = scanner.nextDouble();scanner.nextLine();

                    if (bank.addTransaction(senderName, recipientName, transaction)){
                        System.out.println("Success: Transaction complete");
                    }
                    else{
                        System.out.println("Error: Something went wrong. Please check inputs...");
                    }
                    break;
                case 4:
                    System.out.println("Enter name of branch");
                    branchName = scanner.nextLine();
                    bank.showCustomers(branchName, true);
                    break;
                case 5:
                    System.out.println("Enter name of branch");
                    branchName = scanner.nextLine();
                    bank.addBranch(branchName);
                    break;
                case 6:
                    bank.listBranches();
                    break;
                case  7:
                    System.out.println("Enter name of customer");
                    customerName = scanner.nextLine();

                    System.out.println("Enter withdraw amount");
                    transaction = scanner.nextDouble();scanner.nextLine();
                    bank.customerWithdraw(customerName, transaction);
                    break;
                case 8:
                    System.out.println("Enter name of customer");
                    customerName = scanner.nextLine();

                    System.out.println("Enter withdraw amount");
                    transaction = scanner.nextDouble();scanner.nextLine();
                    bank.customerDeposit(customerName, transaction);
                    break;
            }
        }
    }
    private static void printOptions() {
        System.out.println("0 - Quit");
        System.out.println("1 - Print Options");
        System.out.println("2 - Add customer");
        System.out.println("3 - Add transaction (2 customers)");
        System.out.println("4 - Show Customers");
        System.out.println("5 - Add branch");
        System.out.println("6 - list branches");
        System.out.println("7 - Customer Withdraw");
        System.out.println("8 - Customer Deposit");
    }
}
