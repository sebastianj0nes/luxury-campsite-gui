/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jameslear
 */
public abstract class Accommodation 
{
    
    private int accommodationNo;
    private String cleaningStatus;
    private GuestBooking guestBooking;

    public Accommodation(int accommodationNo) 
    {
        this.accommodationNo = accommodationNo;

        this.cleaningStatus = "Clean";
    }

    public int getAccommodationNo() 
    {
        return accommodationNo;
    }

    public void setAccommodationNo(int accommodationNo) 
    {
        this.accommodationNo = accommodationNo;
    }




    public String getCleaningStatus() 
    {
        return cleaningStatus;
    }

    public void setCleaningStatus(String cleaningStatus) 
    {
        this.cleaningStatus = cleaningStatus;
    }
    
    public void checkin(GuestBooking guestBooking)
    {
        this.guestBooking = guestBooking;
    }
    
    public void checkOut()
    {
        // Remove the guest booking
        this.guestBooking = null;
    }
    
    public GuestBooking getGuestBooking()
    {
        return this.guestBooking;
    }
    
     public boolean hasGuestBooking()
    {
        // If the guest booking is not NULL we have an associated booking.
        return (this.guestBooking != null);
    }
    
    public boolean isOccupied()
    {
        // If we have a guest booking then the accommodation is occupied
        return hasGuestBooking();
    }
    
    public void setClean(boolean bClean)
    {
        if (bClean)
        {
            setCleaningStatus("Clean");
        }
        else
        {
            setCleaningStatus("Requires Cleaning");
        }
    }
    
    public boolean isClean()
    {
        return (this.cleaningStatus.equals("Clean"));
    }
    
    public boolean isAvailable()
    {
        if (!isClean())
        {
            // Requires cleaning so not available
            return false;
        }
        
        if (isOccupied())
        {
            return false;
        }
        
        return true;
        
    }
    
    

	abstract public float getPrice();

        // Get type of accommodation

	abstract public String getType();

        // Get capacity of accommodation
	abstract public int getCapacity();
    
}
