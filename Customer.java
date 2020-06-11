import java.util.ArrayList;

public class Customer {
    private String id;
    private String email;
    private String name;
    private double amount;
    private ArrayList<Double> transactions = new ArrayList<Double>();

    public Customer(String id, String email, String name, double amount) {
        this.id = id;
        this.email = email;
        this.name = name;
        addTransaction((amount));
        this.amount = amount;
    }

    public boolean creditAccount(double amount){
        if (this.amount-amount<0){
            System.out.println("Transaction failed: "+this.name + " has a negative balance.");
            return false;
        }
        this.amount-=amount;
        return true;
    }

    public boolean debitAccount(double amount) {
        this.amount+=amount;
        return true;
    }

    public boolean addTransaction(double amt) {
        transactions.add(amt);
        if (amt<0)return creditAccount(amt);
        else return debitAccount(amt);
    }

    public void deposit(double amt){
        this.amount+=amt;
    }

    public void withdraw(double amt){
        this.amount-=amt;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }
}
