package model;

// Initialise cleaning status enum to hold values
public enum CleaningStatus 
{
    // Enum values
        // Clean/Requires Cleaning/Requires Maintenance
    CLEAN("Clean"),
    REQUIRES_CLEANING("Requires Cleaning"),
    REQUIRES_MAINTENANCE("Requires Maintenance");
    
    // Initialise String cleaningStatus
    private final String cleaningStatus;
    
    CleaningStatus(String cleaningStatus)
    {
        // Set cleaning status to cleaning status variable
        this.cleaningStatus = cleaningStatus;
    }
    

    // Get cleaning status 
    public String getCleaningStatus()
    {
        return this.cleaningStatus;
    }
    

    // Get description
    public String getDescription()
    {
        return cleaningStatus;
    }
    
    // Initialise toString method
    public String toString()
   {
       // Return description (cleaning status object)
       return getDescription();
   }    
    
    
    
    
    
    
    
    
    
}
