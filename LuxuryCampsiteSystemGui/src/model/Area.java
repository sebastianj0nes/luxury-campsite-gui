package model;

import java.util.ArrayList;

public class Area {

        // Initialising variables
	private String areaName;

	private ArrayList<Accommodation> accommodations;
        
	private String areaDescription;

        // Constructor for area object
        public  Area(String areaName,
                String areaDescription) 
        {
            // Assigning values
            this.areaName = areaName;
            this.areaDescription = areaDescription;
            this.accommodations = new ArrayList<Accommodation>();
	}

        // Get area name
	public String getAreaName() 
        {
		return areaName;
	}

        // Get area description
	public String getDescription() 
        {
		return areaDescription;
	}

        // Add accommodation to array list
	public void addAccommodation(Accommodation accommodation) 
        {
            accommodations.add(accommodation);
	}
        
        // Get accommodation array list
	public ArrayList<Accommodation> getAccommodations() 
        {
            return accommodations;
	}

        @Override
	public String toString() 
        {
		return areaName;
	}
}
