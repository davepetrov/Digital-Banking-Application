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

        if (branch!=null && branch.addCustomer(id, email, name, amt)){
            return true;
        }
        return false;
    }

    public boolean addTransaction(String senderName, String recipientEmail, double amt){
        Branch s_branch = fetchBranch(senderName);

        if (r_branch!= null && s_branch!=null){
            return r_branch.addTransaction(recipientName, amt) && s_branch.addTransaction(senderName, -1*amt);
        }
        return false;
    }

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
                return branches.get(i);
            }
        }
        return null;
    }
    private String getCustomerID(String customerEmail){
        for (int i =0; i<this.branches.size(); i++){
            Branch branch = branches.get(i);
            for (int j = 0; j<branch.getCustomers().size(); j++){
                Customer customer = branch.getCustomers().get(i);
                if (customer.getEmail().equals(customer)){
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
        }
        else{
            customerId = identifier;
        }
        for (int i =0; i<this.branches.size(); i++){
            Branch branch = branches.get(i);
            if (branch.queryCustomer(customerId)){
                return true;
            }
        }

        return false;
    }

    public boolean customerWithdraw(String atm, String customerId, double amt){
        Branch branch = fetchBranch(customerId);
        if (branch!=null) {
            if (!atm.equals(this.name)) {
                System.out.println("Service free of " + this.serviceFee + " will be charged");
            }
            return branch.customerWithdraw(customerId, amt) && addTransaction(branch.getName(), customerId, amt * -1) && addTransaction(branch.getName(), customerId, -1 * this.serviceFee);

        }
        return false;
    }

    public boolean customerDeposit(String atm, String customerId, double amt){
        Branch branch = fetchBranch(customerId);
        if (branch != null) {
            if (!atm.equals(this.name)) {
                System.out.println("Service free of " + this.serviceFee + " will be charged");
            }
            return branch.customerDeposit(customerId, amt) && addTransaction(branch.getName(), customerId, amt) && addTransaction(branch.getName(), customerId, -1*this.serviceFee);

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
                    System.out.println("Transaction #" + j + ": " + customer.getTransactions().get(j));
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

    public double customerAmount(String name){
        Branch branch = fetchBranch(name);
        if (branch!=null ){
            Customer customer = branch.queryCustomer(name);
            if(customer!=null) {
                return customer.getAmount();
            }
        }
        return -1;
    }

    public void listBranches(){
        for (int i=0; i<branches.size(); i++){
            System.out.println((i+1) + "->" +branches.get(i).getName());
        }
    }

    public String getName() {
        return name;
    }
}
