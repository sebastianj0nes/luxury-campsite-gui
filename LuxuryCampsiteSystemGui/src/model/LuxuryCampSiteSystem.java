package model;

import java.util.ArrayList;

public class LuxuryCampSiteSystem {

        // Initialise variables to be used
	private ArrayList<Area> areas;
        private static LuxuryCampSiteSystem instance;
        
	private LuxuryCampSiteSystem() 
        {
            // Construct areas with array list
            areas = new ArrayList<Area>();
            
	}
        
        // Get instance of system 
        public static LuxuryCampSiteSystem getInstance()
        {
            // Check if there is not already an instance
            if (instance == null)
            {   
                // If no instance - create one
                instance = new LuxuryCampSiteSystem();
                // Then initialise instance with data
                instance.initializeData();
            }
            
            // Return the instance
            return instance;
        }
        
        // Constructor
        private void initializeData()
        {
            // Adding each individual area
            Area hilltop = new Area("Hilltop", "Nice Hilltop experience, perfect for"
                    + " a relaxing holiday recline");
            Area wildMeadow = new Area("Wild Meadow", "Beautiful Yurt accommodation,"
                    + " hosting two people, perfect for couples break");
            Area woodland = new Area("Woodland", "Lovely Woodland area, an alternative"
                    + " for couples or friends looking to experience nature");
            Area lakeview = new Area("Lakeview", "The perfect recline for four friends,"
                    + " by the lake to enjoy the scenery.");
            
            // LOOPS
            // Runs through each accommodation with the number of accommodations available
                // E.g for shepherd hut it has 3 instances of a shepherd hut - so will loop 3 times
            for (int i=0; i<3; i++)
            {
                SheperdHut shepherdHut = new SheperdHut(i+1);
                hilltop.addAccommodation(shepherdHut);
            }
            
                // Yurt has 4 instances - so will loop 4 times
            for (int i=0; i<4; i++)
            {
                Yurt yurt = new Yurt(i+1);
                wildMeadow.addAccommodation(yurt);
            }
            
                // Geodesic Dome has 4 instances - so will loop 4 times 
            for (int i=0; i<4; i++)
            {
                GeodesicDome geodesicDome = new GeodesicDome(i+1);
                woodland.addAccommodation(geodesicDome);
            }        
         
                // Cabin has 3 instances - so will loop 3 times
            for (int i=0; i<3; i++)
            {
                Cabin cabin = new Cabin(i+1);
                lakeview.addAccommodation(cabin);
            }  
            
            // Store instance in variable 'luxuryCampSiteSystem'
            LuxuryCampSiteSystem luxuryCampSiteSystem = LuxuryCampSiteSystem.getInstance();
            // Add all areasto 'luxuryCampSiteSystem' variable
            luxuryCampSiteSystem.addArea(hilltop);
            luxuryCampSiteSystem.addArea(wildMeadow);
            luxuryCampSiteSystem.addArea(woodland);
            luxuryCampSiteSystem.addArea(lakeview);
            
            
            
        }
                
        // Get array list of areas
	public ArrayList<Area> getAreas() 
        {
            return this.areas;
	}

        // Add an area to the array list
	public void addArea(Area area) 
        {
            this.areas.add(area);

	}


        @Override
	public String toString() 
        {
		return null;
	}

        // Testing code works with a main method
        public static void main(String[] args)
        {
            LuxuryCampSiteSystem luxuryCampSiteSystem = LuxuryCampSiteSystem.getInstance();
            System.out.println("END");
        }

}
