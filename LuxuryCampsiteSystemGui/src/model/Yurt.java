package model;

public class Yurt extends Accommodation 
{
        // Constructor
        public Yurt(int accomodationNumber)
        {
            // Overriding Accommodation class to give accommodation properties of Yurt e.g price
            super(accomodationNumber);
        }

    
        // As price/type/capacity are constant values we can hard code the properties
        // Get price of yurt
        @Override
	public float getPrice() {
		return 110.0f;
	}

        // Get type of accommodation
        @Override
	public String getType() {
		return "Yurt";
	}

        // Get capacity of Yurt
        @Override
	public int getCapacity() {
		return 2;
	}

}
