import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Branch> branches = new ArrayList<Branch>();

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

    public boolean addTransaction(String branchName, String customerName, double amt){
        Branch branch = queryBranch(branchName);
        if (branch!=null){
            return branch.addTransaction(customerName, amt);
        }
        return false;
    }

    public boolean showCustomers(String branchName, boolean showTranscation){
        Branch branch = queryBranch(branchName);
        if (queryBranch(branchName) == null) {
            return false;
        }
        Customer customer;
        for (int i=0; i<branch.getCustomers().size(); i++){
            customer = branch.getCustomers().get(i);
            System.out.println(customer.getName());
            if (showTranscation) {
                for (int j = 0; i < customer.getTransactions().size(); i++) {
                    System.out.println("transaction #" + j + ": " + customer.getTransactions().get(j));
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
}
