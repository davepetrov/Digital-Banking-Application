import java.util.ArrayList;

public class Branch {
    private String name;
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    public Branch(String name) {
        this.name = name;
    }

    public boolean addCustomer(String name, double amt){
        if (queryCustomer(name)!=null){
            return false;
        }
        customers.add(new Customer(name, amt));
        return true;
    }

    public boolean addTransaction(String name, double amt){
        Customer customer = queryCustomer(name);

        if (customer!=null){
            //System.out.println("Adding "+amt+" to "+name);
            //System.out.println("Remmoving "+amt+" from "+name);
            customer.addTransaction(amt);
        }
        System.out.println(name+"is not a customer of the branch. Unable to add transaction.");
        return false;
    }
    public Customer queryCustomer(String name){
        for (int i=0; i<customers.size(); i++){
            if (customers.get(i).getName() == name){
                return customers.get(i);
            }
        }
        return null;
    }
    public void customerWithdraw(String name,double amt){
        Customer customer = queryCustomer(name);
        if (customer!=null){
            customer.withdraw(amt);
        }
    }
    public void customerDeposit(String name,double amt){
        Customer customer = queryCustomer(name);
        if (customer!=null){
            customer.deposit(amt);
        }
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public String getName() {
        return name;
    }
}

