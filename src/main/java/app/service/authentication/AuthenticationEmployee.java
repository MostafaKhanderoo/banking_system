package app.service.authentication;

import app.entity.Employee;

public class AuthenticationEmployee {
    private static Employee loggedInEmployee;

    public static void setLoggedInEmployee(Employee employee){
        loggedInEmployee =employee;
    }
    public static Employee getLoggedInEmployee (){
        return loggedInEmployee;
    }

    public static void logout(){
        loggedInEmployee =null;
    }
}
