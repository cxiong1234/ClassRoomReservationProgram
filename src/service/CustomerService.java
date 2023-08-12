package service;
import java.util.Collection;
import java.util.HashMap;
import models.Customer;

public class CustomerService {
    private final HashMap<String, Customer> allCustomersMap = new HashMap<>();
    private static CustomerService singletonReference = new CustomerService();


    private CustomerService(){   //constructor
    }

    public static CustomerService getInstance(){
       if(singletonReference==null){
           singletonReference = new CustomerService();
       }
       return singletonReference;
    }

    public void addCustomer(String firstName, String lastName, String email){
        Customer newCustomer = new Customer(firstName, lastName, email);
        allCustomersMap.put(newCustomer.getEmailAddress(), newCustomer);
    }

    public Customer getCustomer(String customerEmail){
        return allCustomersMap.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers(){
        Collection<Customer> allCustomersCollection = allCustomersMap.values();
        return allCustomersCollection;
    }
}
