package model;

public class SheperdHut extends Accommodation 
{
        // Constructor
        public SheperdHut(int accomodationNumber)
        {
            // Overriding Accommodation class to give accommodation properties of Shepherd Hut e.g price
            super(accomodationNumber);
        }

        // As price/type/capacity are constant values we can hard code the properties
        // Get price of shepherd hut
        @Override
	public float getPrice() {
		return 140.0f;
	}

        // Get type of accommodation
        @Override
	public String getType() {
		return "Shepherd Hut";
	}

        // Get capacity of shepherd hut
        @Override
	public int getCapacity() {
		return 3;
	}

}
