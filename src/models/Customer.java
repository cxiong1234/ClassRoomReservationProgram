package models;

import java.util.regex.Pattern;
import java.util.Objects;

public class Customer {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private final String emailRegEx = "^(.+)@(.+)\\.com$"; //email address syntax should be like xiong1234@gmail.com
    private final Pattern pattern = Pattern.compile(emailRegEx);

    public Customer(String firstName, String lastName, String emailAddress){
        if(!pattern.matcher(emailAddress).matches()){
            throw new IllegalArgumentException("Error, Please use correct email address, like xiong1234@gmail.com");
        }
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;

    }
    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String newFirstName){
        this.firstName = newFirstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public  void setLastName(String newLastName){
        this.lastName = newLastName;
    }
    public String getEmailAddress(){
        return this.emailAddress;
    }
    public void setEmailAddress(String newEmailAddress){
        this.emailAddress = newEmailAddress;
    }
    @Override
    public String toString(){
        return  "First Name: " + this.firstName + "\n" +
                "Last Name: " + this.lastName + "\n" +
                "Email Address: " + this.emailAddress + "\n";

    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(getClass() != obj.getClass() || obj == null){
            return false;
        }

        Customer customer = (Customer) obj;
        return this.emailAddress.equals(customer.emailAddress);

    }

    @Override
    public int hashCode(){
        return Objects.hash(emailAddress);
    }

}
