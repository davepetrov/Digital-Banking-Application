import java.util.ArrayList;

public class Customer {
    private String name;

    private ArrayList<Double> transactions = new ArrayList<Double>();

    public Customer(String name, double initialTransaction) {
        this.name = name;
        addTransaction((initialTransaction));
    }

    public boolean addTransaction(double amt) {
        if (0 < amt) {
            transactions.add(amt);
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }
}
