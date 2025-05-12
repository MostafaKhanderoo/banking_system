package app.service.authentication;

import app.entity.Customer;

public class AuthenticationCustomer {
    private static Customer loggedInCustomer;


    public static void setLoggedInCustomer(Customer customer){

        loggedInCustomer = customer;
    }
    public static Customer getLoggedInCustomer(){
        return loggedInCustomer;

    }
    public static void logout(){
        loggedInCustomer =null;
    }

}
