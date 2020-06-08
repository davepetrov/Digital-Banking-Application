import java.util.ArrayList;

public class Customer {
    private String name;
    private double amount;
    private ArrayList<Double> transactions = new ArrayList<Double>();

    public Customer(String name, double amount) {
        this.name = name;
        addTransaction((amount));
        this.amount = amount;
    }

    public boolean creditAccount(double amount){
        this.amount-=amount;
        if (this.amount<0){
            System.out.println(this.name + "Is in debt. Add more balance. Transaction failed");
            return false;
        }
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

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }
}
