import java.util.ArrayList;

public class Branch {
    private String name;
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    public Branch(String name) {
        this.name = name;
    }

    public boolean addCustomer(String customerId,String email, String name, double amt){
        if (queryCustomer(customerId) == null) {
            customers.add(new Customer(customerId, email, name, amt));
            return true;
        }
        return false;

    }

    public boolean addTransaction(String customerId, double amt){
        Customer customer = queryCustomer(customerId);
        if (customer!=null){
            return customer.addTransaction(amt);
        }
        System.out.println(name+" is not a customer of the branch. Unable to add transaction.");
        return false;
    }
    public Customer queryCustomer(String customerId){
        for (int i=0; i<customers.size(); i++){
            Customer customer = customers.get(i);
            if (customer.getId().equals(customerId)){
                return customer;
            }
        }
        return null;
    }

    public boolean hasCustomer(String customerId){
        for (int i=0; i<customers.size(); i++){
            Customer customer = customers.get(i);
            if (customer.getId().equals(customerId)){
                return true;
            }
        }
        return false;
    }

    public boolean customerWithdraw(String customerId,double amt){
        Customer customer = queryCustomer(customerId);
        if (customer!=null){
            customer.withdraw(amt);
            return true;
        }
        return false;
    }
    public boolean customerDeposit(String customerId,double amt){
        Customer customer = queryCustomer(customerId);
        if (customer!=null){
            customer.deposit(amt);
            return true;
        }
        return false;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public String getName() {
        return name;
    }
}

