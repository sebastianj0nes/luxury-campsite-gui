package model;

public class GeodesicDome extends Accommodation {

        // Constructor
        public GeodesicDome(int accomodationNumber)
        {
            // Overriding Accommodation class to give accommodation properties of Geodesic Dome e.g price
            super(accomodationNumber);
        }

        // As price/type/capacity are constant values we can hard code the properties
        // Get price of geodesic dome
        @Override
	public float getPrice() {
		return 120.0f;
	}

        // Get type of accommodation 
        @Override
	public String getType() {
		return "Geodesic Dome";
	}

        // Get capacity of geodesic dome
        @Override
	public int getCapacity() {
		return 2;
	}

}
