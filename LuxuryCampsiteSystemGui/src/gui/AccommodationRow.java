package gui;

import javafx.beans.property.SimpleStringProperty;

public class AccommodationRow 
{
    private final SimpleStringProperty number;
    private final SimpleStringProperty type;
    private final SimpleStringProperty occupancy;
    private final SimpleStringProperty availability;
    private final SimpleStringProperty status;
    private final SimpleStringProperty numberGuests;
    private final SimpleStringProperty breakfast;
 
    // private final Accommodation accommodation;
    
    public AccommodationRow(String strNumber,
                            String strType,
                            String strOccupancy,
                            String strAvailability,
                            String strStatus,
                            String strNumberGuests,
                            String strBreakfast
                            /* Accommodation accommodation*/)
    {
        this.number = new SimpleStringProperty(strNumber);
        this.type = new SimpleStringProperty(strType);
        this.occupancy = new SimpleStringProperty(strOccupancy);
        this.availability = new SimpleStringProperty(strAvailability);
        this.status = new SimpleStringProperty(strStatus);
        this.numberGuests = new SimpleStringProperty(strNumberGuests);
        this.breakfast = new SimpleStringProperty(strBreakfast);
        //this.accommodation = accommodation;
    }
    
    public String getNumber() 
    {
        return number.get();
    }

    public String getType() 
    {
        return type.get();
    }

    public String getOccupancy() 
    {
        return occupancy.get();
    }
    
    public String getAvailability()
    {
        return availability.get();
    }

    public String getStatus() 
    {
        return status.get();
    }

    public String getNumberGuests() 
    {
        return numberGuests.get();
    }

    public String getBreakfast() 
    {
        return breakfast.get();
    }

    /*
    public Accommodation getAccommodation() 
    {
        return accommodation;
    }
*/
    
} 

