package model;

public class Guest 
{

    // Initialise variables to be used
    private String firstName;
    private String lastName;
    private String pNumber;
    
    // Constructor
    public Guest(String firstName, String lastName, String pNumber) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pNumber = pNumber;
    }

    // Get first name of guest
    public String getFirstName() 
    {
        return firstName;
    }

    // Get last name of guest
    public String getLastName() 
    {
        return lastName;
    }

    // Get phone number of guest
    public String getTelephoneNumber() 
    {
        return pNumber;
    }


}
