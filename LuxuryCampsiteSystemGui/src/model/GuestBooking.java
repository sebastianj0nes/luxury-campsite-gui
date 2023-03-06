/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author jameslear
 */
public class GuestBooking 
{
    private String firstName;
    private String lastName;
    private int numberGuests;
    private boolean breakfastRequired;

    public boolean isBreakfastRequired() 
    {
        return breakfastRequired;
    }

    public void setBreakfastRequired(boolean breakfastRequired) 
    {
        this.breakfastRequired = breakfastRequired;
    }

    public GuestBooking(String firstName, String lastName, int numberGuests) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberGuests = numberGuests;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public int getNumberGuests() 
    {
        return numberGuests;
    }

    public void setNumberGuests(int numberGuests) 
    {
        this.numberGuests = numberGuests;
    }

}
