package model;


import java.time.LocalDate;


public class Booking 
{
        // Initialising variables
        private boolean requiresBreakfast;
	private int numberGuests;
	private LocalDate checkInDay;
	private int duration;
	private Guest guest;
        
        // Constructor
        public Booking(boolean requiresBreakfast,
                            int numberGuests,
                            LocalDate checkInDay,
                            int duration,
                            Guest guest)
        {
            // Assigning values
            this.requiresBreakfast = requiresBreakfast;
            this.numberGuests = numberGuests;
            this.checkInDay = checkInDay;
            this.duration = duration;
            this.guest = guest;
        }

        // Get truth value of if guest requires breakfast
	public boolean getRequiresBreakfast() 
        {
            return this.requiresBreakfast;
	}

        // Get number of guests staying
	public int getNumberGuests()
        {
            return this.numberGuests;
	}

        // Get check in day of guests
	public LocalDate getCheckInDay()
        {
            return this.checkInDay;
	}

        // Get duration of stay 
	public int getDuration() 
        {
            return this.duration;
	}

        // Get guest object
        public Guest getGuest()
        {
            return this.guest;
        }

}
