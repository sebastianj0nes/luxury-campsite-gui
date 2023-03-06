package model;

public class Cabin extends Accommodation {
    
        // Constructor
        public Cabin(int accomodationNumber)
        {
            // Overriding Accommodation class to give accommodation properties of Cabin e.g price
            super(accomodationNumber);
        }

        // As price/type/capacity are constant values we can hard code the properties
        // Get price of cabin
        @Override
	public float getPrice() {
		return 160f;
	}

        // Get type of accommodation
        @Override
	public String getType() {
		return "Cabin";
	}

        // Get capacity of accommodation
        @Override
	public int getCapacity() {
		return 4;
	}

}
