import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Branch> branches = new ArrayList<Branch>();
    private double amount = 1000000000;

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

    public boolean addCustomer(String branchName, String customerName, double amt){
        Branch branch = queryBranch(branchName);
        if (branch!=null && branch.addCustomer(customerName, amt)){
            branch.addCustomer(customerName, amt);
            return true;
        }
        return false;
    }

    public boolean addTransaction(String senderName, String recipientName, double amt){
        Branch r_branch = fetchBranch(recipientName);
        Branch s_branch = fetchBranch(senderName);
        if (r_branch!= null && s_branch!=null){
            return r_branch.addTransaction(recipientName, amt) && s_branch.addTransaction(senderName, -1*amt);
        }
        return false;
    }

    public boolean addTransaction(String customerName, double amt){
        Branch branch = fetchBranch(customerName);
        if (branch!= null){
            return branch.addTransaction(customerName, amt);
        }
        return false;
    }

    private Branch fetchBranch(String customerName){
        for (int i =0; i<this.branches.size(); i++){
            Branch branch = branches.get(i);
            for (int j=0; i<branch.getCustomers().size(); i++){
                if (branch.getCustomers().get(j).getName().equals(customerName)){
                    return branch;
                }
            }
        }
        return null;
    }
    public boolean isCustomer(String customerName){
        for (int i =0; i<this.branches.size(); i++){
            Branch branch = branches.get(i);
            for (int j=0; i<branch.getCustomers().size(); i++){
                if (branch.getCustomers().get(j).getName().equals(customerName)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean customerWithdraw(String customerName, double amt){
        Branch branch = fetchBranch(customerName);
        if (branch!=null){
            return branch.customerWithdraw(customerName, amt) && addTransaction(branch.getName(), customerName, amt);
        }
        return false;
    }
    public boolean customerDeposit(String customerName, double amt){
        Branch branch = fetchBranch(customerName);
        if (branch!=null){
            return branch.customerDeposit(customerName, amt) && addTransaction(branch.getName(), customerName, amt);
        }
        return false;
    }

    public boolean showCustomers(String branchName, boolean showTransaction){
        Branch branch = queryBranch(branchName);
        if (queryBranch(branchName) == null) {
            return false;
        }
        Customer customer;
        for (int i=0; i<branch.getCustomers().size(); i++){
            customer = branch.getCustomers().get(i);
            System.out.println("\nCustomer "+customer.getName());
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

    public void listBranches(){
        for (int i=0; i<branches.size(); i++){
            System.out.println((i+1) + "->" +branches.get(i).getName());
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

}
