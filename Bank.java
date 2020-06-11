import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Branch> branches = new ArrayList<Branch>();
    private double amount = 1000000000;
    private double serviceFee = 3.27;   //Average. May depend on individual bank.

    public Bank(String name) {
        this.name = name;
    }

    public boolean addBranch(String name){
        if (queryBranch(name)!=null){
            return false;
        }
        System.out.println("Adding branch to Bank");
        branches.add(new Branch(name));
        return true;
    }
    private int numCustomers(){
        int customers = 0;
        for (int i =0; i<this.branches.size(); i++){
            customers+= this.branches.get(i).getCustomers().size();
        }
        return customers;
    }
    private String generateId(){
        return "#ID"+numCustomers();
    }
    public boolean addCustomer(String branchName, String email, String name, double amt){
        Branch branch = queryBranch(branchName);
        String id = generateId();

        if (branch!=null){
            return branch.addCustomer(id, email, name, amt);
        }
        return false;
    }

    public boolean addTransaction(String senderEmail, String recipientEmail, double amt){
        String customerId = getCustomerID(senderEmail);
        Branch s_branch = fetchBranch(customerId);

        //Bank of sender validates that the recepient has a valid bank account;
        String recipientId = getCustomerID(recipientEmail);
        Bank r_bank = Main.queryBank(recipientEmail);
        Branch r_branch = r_bank.fetchBranch(recipientEmail);

        if (r_bank!=null && r_branch!= null && s_branch!=null){
            return s_branch.addTransaction(customerId, amt) && r_branch.addTransaction(recipientId, -1*amt);
        }
        return false;
    }

    //For withdraw, deposit
    public boolean addTransaction(String customerId, double amt){
        Branch branch = fetchBranch(customerId);
        if (branch!= null){
            return branch.addTransaction(customerId, amt);
        }
        return false;
    }

    public Branch fetchBranch(String customerId){
        for (int i =0; i<this.branches.size(); i++){
            Branch branch = branches.get(i);
            if (branch.hasCustomer(customerId)){
                return branch;
            }
        }
        return null;
    }
    private String getCustomerID(String customerEmail){
        for (int i =0; i<this.branches.size(); i++){
            Branch branch = branches.get(i);
            for (int j = 0; j<branch.getCustomers().size(); j++){
                Customer customer = branch.getCustomers().get(i);
                if (customer.getEmail().equals(customerEmail)){
                    return customer.getId();
                }
            }
        }
        return null;
    }
    public boolean hasCustomer(String identifier, char query){ //Query == e (email) or i (id)
        String customerId;
        if (query == 'e') {
            customerId = getCustomerID(identifier);
        } else if (query == 'i'){
            customerId = identifier;
        } else{
            System.out.println("Not a valid query identifier");
            return false;
        }
        for (int i =0; i<this.branches.size(); i++){
            Branch branch = branches.get(i);
            if (branch.hasCustomer(customerId)){
                return true;
            }
        }

        return false;
    }

    public boolean customerWithdraw(String atmName, String customerEmail, double amt){
        String customerId = getCustomerID(customerEmail);
        Branch branch = fetchBranch(customerId);
        if (branch!=null) {
            if (!atmName.equals(this.name)) {
                System.out.println("Service free of " + this.serviceFee + " will be charged");
            }
            return branch.customerWithdraw(customerId, amt) && addTransaction(customerId, -1*this.serviceFee);

        }
        return false;
    }

    public boolean customerDeposit(String atmName, String customerEmail, double amt){
        String customerId = getCustomerID(customerEmail);
        Branch branch = fetchBranch(customerId);
        if (branch != null) {
            if (!atmName.equals(this.name)) {
                System.out.println("Service free of " + this.serviceFee + " will be charged");
            }
            return branch.customerDeposit(customerId, amt) && branch.addTransaction(customerId, -1*this.serviceFee);

        }
        return false;
    }

    public boolean showCustomers(String branchName, boolean showTransaction){
        Branch branch = queryBranch(branchName);
        if (queryBranch(branchName) == null) return false;
        Customer customer;
        for (int i=0; i<branch.getCustomers().size(); i++){
            customer = branch.getCustomers().get(i);
            System.out.println("\nCustomer "+customer.getId());
            if (showTransaction) {
                for (int j = 0; i < customer.getTransactions().size(); i++) {
                    double transaction = customer.getTransactions().get(j);
                    System.out.println("Transaction #" + (j+1) + ": " + transaction);
                }
            }
        }
        return true;
    }
    private Branch queryBranch(String name){
        System.out.println(branches.size());
        for (int i=0; i<branches.size(); i++){
            if (branches.get(i).getName().equals(name)){
                return branches.get(i);
            }
        }
        return null;
    }

    public double customerAmount(String customerEmail){
        String customerID = getCustomerID(customerEmail);
        Branch branch = fetchBranch(customerID);
        if (branch!=null ){
            Customer customer = branch.queryCustomer(customerID);
            if(customer!=null) {
                return customer.getAmount();
            }
        }
        return -1;
    }

    public void listBranches(){
        for (int i=0; i<branches.size(); i++){
            Branch branch = branches.get(i);
            System.out.println((i+1) + "->" +branch.getName());
        }
    }

    public String getName() {
        return name;
    }
}
