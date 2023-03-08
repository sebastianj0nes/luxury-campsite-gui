package gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Accommodation;
import model.LuxuryCampSiteSystem;
import model.Booking;
import model.Area;
import model.Booking;
import model.CleaningStatus;
import model.Guest;
/*
Author Seb Jones
*/

public class GUIController implements Initializable {
    
    // Data Fields for Array Lists
    private ObservableList<Area> areaData = FXCollections.observableArrayList();
    private ObservableList<CleaningStatus> cleaningStatusData = FXCollections.observableArrayList();
    private ObservableList<AccommodationRow> tableData = FXCollections.observableArrayList();
    private LuxuryCampSiteSystem accommodationSystem = null;

    // Generated FXML elements
    @FXML
    private TableView<AccommodationRow> myHTable;
    @FXML
    private ChoiceBox<Area> choiceBox;
    @FXML
    private TableColumn<AccommodationRow, String> tableNumber;
    @FXML
    private TableColumn<AccommodationRow, String> tableAccomType;
    @FXML
    private TableColumn<AccommodationRow, String> tableOccupancy;
    @FXML
    private TableColumn<AccommodationRow, String> tableAvailability;
    @FXML
    private TableColumn<AccommodationRow, String> tableStatus;
    @FXML
    private TableColumn<AccommodationRow, String> tableGuests;
    @FXML
    private TableColumn<AccommodationRow, String> tableBreakfast;
    @FXML
    private TextField totalBreakfast;
    @FXML
    private TextField totalReqCleaning;
    @FXML
    private TextArea accomDesc;
    @FXML
    private ChoiceBox<CleaningStatus> cleaningStatus;
    @FXML
    private TextField accomType;
    @FXML
    private TextField accomNum;
    @FXML
    private TextField accomAccommodates;
    @FXML
    private TextField costPerNight;
    @FXML
    private TextArea areaDesc;
    @FXML
    private Button checkInButton;
    @FXML
    private Button checkOutButton;
    @FXML
    private TextField firstName;
    @FXML
    private TextField surname;
    @FXML
    private TextField telNum;
    @FXML
    private TextField numOfGuests;
    @FXML
    private TextField checkInDate;
    @FXML
    private TextField numOfNights;
    @FXML
    private CheckBox breakfastTick;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("DEBUG: Initialize() called");
        
        // Setting actions for the area field and cleaning status choice boxes
        choiceBox.setOnAction(this::cbAreaOnAction);
        
        cleaningStatus.setOnAction(this::cbCleaningStatusOnAction);

        //INITIALIZE TABLE COLUMNS
        // Associating each column (e.g. myHTableNumber) with instance name (e.g. "number")
        tableNumber.setCellValueFactory(new PropertyValueFactory<AccommodationRow, String>("number"));
        tableAccomType.setCellValueFactory(new PropertyValueFactory<AccommodationRow, String>("type"));
        tableOccupancy.setCellValueFactory(new PropertyValueFactory<AccommodationRow, String>("occupancy"));
        tableAvailability.setCellValueFactory(new PropertyValueFactory<AccommodationRow, String>("availability"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<AccommodationRow, String>("status"));
        tableGuests.setCellValueFactory(new PropertyValueFactory<AccommodationRow, String>("numberGuests"));
        tableBreakfast.setCellValueFactory(new PropertyValueFactory<AccommodationRow, String>("breakfast"));

        // Storing accommodation system in a state variable for reusability
        LuxuryCampSiteSystem accommodationSystem = LuxuryCampSiteSystem.getInstance();
        // accommodationSystem.initializeDummyData();
        
        // Storing accommodation system
        setAccommodationSystem(accommodationSystem);
        
        // Get all areas of the accommodation system
        ArrayList<Area> areas = accommodationSystem.getAreas();
        
        // For loop - run through all areas and add them to the data 
        for (int i = 0; i<areas.size(); i++)
        {
            areaData.add(areas.get(i));
        }
        // Setting the area dropdown with the area data 
        choiceBox.setItems(areaData);
        choiceBox.setValue(areaData.get(0));
        
        // Adding cleaning status enum options to the data of cleaning status choice box
        cleaningStatusData.add(CleaningStatus.CLEAN);
        cleaningStatusData.add(CleaningStatus.REQUIRES_CLEANING);

        cleaningStatus.setItems(cleaningStatusData);
        cleaningStatus.setValue(cleaningStatusData.get(0));
        
        // Start by populating table with first area
        populateTable(areaData.get(0));
    
    }
    
       // Set accommodation system
       private void setAccommodationSystem(LuxuryCampSiteSystem accommodationSystem)
       {
           this.accommodationSystem = accommodationSystem;
       }
       
       // Get accommodation system
       private LuxuryCampSiteSystem getAccommodationSystem()
       {
           return this.accommodationSystem;
       }
        
       // Populate table method passing in an area as argument
       private void populateTable(Area area)
       {
           System.out.println("populateTableCalled");
           
            // Create Array list to hold all areas
            ArrayList<Accommodation> accommodations = area.getAccommodations();

            // Initially clear the table
            tableData.clear();

        // Loop through accommodations 
        for (Accommodation accommodation : accommodations) 
        {
            // Set initial occupancy to onccupied as at start there are no bookings
            String occupancy = "Unoccupied";
            // If there is a booking set the accommodartion to occupied
            if (accommodation.isOccupied())
            {
                occupancy = "Occupied";
            }
            // Set initial values to default with no booking
            String numberGuests = "";
            String breakfastRequired = "No";
            
            // If an accommodation has a booking
            if (accommodation.hasGuestBooking())
            {
                // Get the guest booking
                Booking guestBooking = accommodation.getGuestBooking();
                // Store number of guests in 'noGuests'
                int noGuests = guestBooking.getNumberGuests();
                // If there are guests
                if (noGuests > 0)
                {
                    // Set the number of guests to value of guests staying
                    numberGuests = Integer.toString(noGuests);
                }
                
                // Initialise boolean to store value if  requires breakfast
                boolean isBreakfastRequired = guestBooking.getRequiresBreakfast();
                // If there are breakfast
                if (isBreakfastRequired)
                {
                    // Store yes
                    breakfastRequired = "Yes";
                }
            }
            
            // Initialise variable to hold availability of area
            String availability = "Unavailable";
            // If accommodation is available
            if (accommodation.isAvailable())
            {
                // Set value to available
                availability = "Available";
            }
            
            // Add accommodation rows passing in all the values initialised to the table data
            tableData.add(new AccommodationRow(String.valueOf(accommodation.getAccommodationNo()),
                    accommodation.getType(),
                    occupancy,
                    availability,
                    accommodation.getCleaningStatus().getDescription(),
                    numberGuests,
                    breakfastRequired));
        }

        // Set table with all data inputted
        myHTable.setItems(tableData);
        
        // Calculate the stats for each area
        calculateCleaning();
        calculateBreakfasts();
       }
    // Get accommodation method passing in an area and accommodation number
    private Accommodation getAccommodation(Area area, int accommNo)
    {
        // Array list to hold all accommodations in each area
        ArrayList<Accommodation> accommodations = area.getAccommodations();
        
        // Set initial found variable to null
        Accommodation foundAccommodation = null;

        // Loop through accommodations
        for (Accommodation accommodation : accommodations) 
        {
            // If the accom number matches passed in the argument passed
            if (accommodation.getAccommodationNo() == accommNo)
            {
                // Set found accommodation to the matching accommodation
                foundAccommodation = accommodation;
                break;
            }
        }
        
        // Pass out the found accommodation
        return foundAccommodation;
    }
 
    // Event handler for area choice box
    private void cbAreaOnAction(ActionEvent event)
    {
        // Initialise area object with chosen value
        Area area = choiceBox.getValue();
        
        // Populate the table with information from chosen area
        populateTable(area);
    }

    // Event handler for cleaning status choice box
    private void cbCleaningStatusOnAction(ActionEvent event)
    {       
        // Initialise area object with chosen value
        Area area = choiceBox.getValue();

        // Set accommodation row to chosen row item
        AccommodationRow accommodationRow = myHTable.getSelectionModel().getSelectedItem();
        // If there is an accommodation row
        if (accommodationRow != null)
        {
            // Set accommodation number to the int value of accommodation row
            int accommNo = Integer.valueOf(accommodationRow.getNumber());
            // Create selected accommodation by calling getAccommodation passing in area and accomm number
            Accommodation selectedAccommodation = getAccommodation(area, accommNo);
            // Set the cleaning status after getting the value
            selectedAccommodation.setCleaningStatus(cleaningStatus.getValue());
            // Populate the table with the appropriate area data
            populateTable(area);
            
        }
    }

    // Event handler for clicking on anywhere in table
    @FXML
    private void OnTableClicked(MouseEvent event) 
    {
        // Initially reset all fields 
        // Guest fields
        firstName.setText("");
        surname.setText("");
        telNum.setText("");
        // Booking fields
        numOfGuests.setText("");
        checkInDate.setText("");
        numOfNights.setText("");
        // Accommodation fields
        accomType.setText("");
        accomNum.setText("");
        accomAccommodates.setText("");
        costPerNight.setText("");
        areaDesc.setText("");
        breakfastTick.setSelected(false);

        // Create accommodation row variable with selected item
        AccommodationRow accommodationRow = myHTable.getSelectionModel().getSelectedItem();

        // If there is an accommodation row
        if (accommodationRow != null) 
        {
            
            System.out.println(accommodationRow.getNumber());
            
            // Initialise area object with chosen value
            Area area = choiceBox.getValue();
            
            // Get the accommodation number value
            int accommNo = Integer.valueOf(accommodationRow.getNumber());
            // Find accommodation based on accommodation number
            Accommodation selectedAccommodation = getAccommodation(area, accommNo);

            // Display the accommodation information 
            accomType.setText(selectedAccommodation.getType());
            accomNum.setText(String.valueOf(accommNo));
            accomAccommodates.setText(String.valueOf(selectedAccommodation.getCapacity()));
            costPerNight.setText(String.valueOf(selectedAccommodation.getPrice()));
            areaDesc.setText(area.getDescription());
            // Get cleaning status value
            CleaningStatus cleaningStatusVal = selectedAccommodation.getCleaningStatus();
            
            // If the value is clean
            if (cleaningStatusVal == CleaningStatus.CLEAN)
            {
                // Set the cleaning status choice box data to index 0 (CLEAN)
                cleaningStatus.setValue(cleaningStatusData.get(0));
            }
            else
            {   
                // Else - set cleaning status choice box data to index 1 (REQUIRES CLEANING)
                cleaningStatus.setValue(cleaningStatusData.get(1));
            }
            
            // If the accommodation has a booking
            if (selectedAccommodation.hasGuestBooking())
            {
                // Get the guest and booking object
                Booking associatedGuestBooking = selectedAccommodation.getGuestBooking();
                Guest guest = associatedGuestBooking.getGuest();
                
                // Initialise variables from guest object
                String bookingFirstName = guest.getFirstName();
                String bookingLastName = guest.getLastName();
                String telephoneNumber = guest.getTelephoneNumber();
                
                // Initialise variables from booking object
                String numberOfGuests = String.valueOf(associatedGuestBooking.getNumberGuests());
                String formattedDate = associatedGuestBooking.getCheckInDay().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
                boolean requiresBreakfast = associatedGuestBooking.getRequiresBreakfast();
                String numberNights = String.valueOf(associatedGuestBooking.getDuration());

                // Set table text values to matching variables
                firstName.setText(bookingFirstName);
                surname.setText(bookingLastName);
                telNum.setText(telephoneNumber);

                numOfGuests.setText(numberOfGuests);
                checkInDate.setText(formattedDate);
                numOfNights.setText(numberNights);
                breakfastTick.setSelected(requiresBreakfast);
                
                
            }

        }
    } 

    // Event handler for check in
    @FXML
    private void onCheckin(ActionEvent event) 
    {
        // Get the values of each text field entered
        String firstNameVal = firstName.getText();
        String surnameVal = surname.getText();
        String telNumVal = telNum.getText();
        String numofGuestsVal = numOfGuests.getText();
        String checkInDateVal = checkInDate.getText();
        String numOfNightsVal = numOfNights.getText();
        boolean breakfastRequired = breakfastTick.isSelected();
        
        // Date formatting
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate checkinDate = LocalDate.parse(checkInDateVal, dateTimeFormatter);
        
        // Construct guest and booking objects with entered values
        Guest guest = new Guest(firstNameVal, surnameVal, telNumVal);
        Booking booking = new Booking(breakfastRequired,
                            Integer.valueOf(numofGuestsVal),
                            checkinDate,
                            Integer.valueOf(numOfNightsVal),
                            guest);
        
        // Initialise accommodation row with selected row
        AccommodationRow accommodationRow = myHTable.getSelectionModel().getSelectedItem();
        
        // Initialise area object with value of chosen area
        Area area = choiceBox.getValue();

        // Get accommodation number from accomm row 
        int accommNo = Integer.valueOf(accommodationRow.getNumber());

        // Get selected accommodation 
        Accommodation selectedAccommodation = getAccommodation(area, accommNo);
        
        // Check in to the right accommodation passing in booking object
        selectedAccommodation.checkin(booking);

        // Populate the table with chosen area
        populateTable(area);
        
        // Calculate the stats for each area
        calculateCleaning();
        calculateBreakfasts();
    }

    // Event handler for check out
    @FXML
    private void onCheckout(ActionEvent event) 
    {
        // Initialise accommodation row with selected row
        AccommodationRow accommodationRow = myHTable.getSelectionModel().getSelectedItem();
        
        // Initialise area object with chosen value
        Area area = choiceBox.getValue();

        // Get accom number
        int accommNo = Integer.valueOf(accommodationRow.getNumber());

        // Create accommodation object with getAccom method
        Accommodation selectedAccommodation = getAccommodation(area, accommNo);
        
        // On check out - set the cleaning status to requires cleaning
        selectedAccommodation.setCleaningStatus(CleaningStatus.REQUIRES_CLEANING);
        
        // Call check out method setting guest object to null (defined in accommodation class)
        selectedAccommodation.checkOut();
        
        // Populate the table with area details
        populateTable(area);
        
        // Set all text fields to empty
        // Guest fields
        firstName.setText("");
        surname.setText("");
        telNum.setText("");
        // Booking fields
        numOfGuests.setText("");
        checkInDate.setText("");
        numOfNights.setText("");
        breakfastTick.setSelected(false);
        // Set the cleaning status choice box to requires cleaning
        cleaningStatus.setValue(cleaningStatusData.get(1));
        
        // Calculate the stats for each area
        calculateCleaning();
        calculateBreakfasts();
    }
    
    
    // Calculate number of breakfasts required for each accommodation
    private void calculateBreakfasts()
    {
        // Create int value to hold number of breakfasts required
        int numBreakfasts = 0;
        
        // Get the chosen area
        Area selectedArea = choiceBox.getValue();
        
        // Create array list to hold all accommodations in the area
        ArrayList<Accommodation> allAccoms = selectedArea.getAccommodations();
        
        // Loop through all accommodations 
        for (int i =1; i <allAccoms.size()+1; i++)
        {
            // Get selected guest booking
            Booking selectedBooking = getAccommodation(selectedArea,i).getGuestBooking();
            
            // If booking not null
            if (selectedBooking != null)
            {
                // Create boolean to hold if the guest requires breakfast
                boolean requiresBreakfast = selectedBooking.getRequiresBreakfast();
                
                // If they require breakfast
                if (requiresBreakfast == true)
                {
                    // Add 1 to total number of breakfasts required
                    numBreakfasts++;
                }
            }
        }
        totalBreakfast.setText(String.valueOf(numBreakfasts));
    }
    
    // Calculate cleaning method for each area 
    private void calculateCleaning()
    {
        // Create int value to hold number of cleaning required
        int numCleaning = 0;
        // Get the chosen area
        Area selectedArea = choiceBox.getValue();
        
        // Create array list to hold all accommodations in the area
        ArrayList<Accommodation> allAccoms = selectedArea.getAccommodations();
       
        // For loop to run through all accommodations
        for (int i=1; i < allAccoms.size()+1; i++)
        {
            // Create accommodation object for each accommodation in area
            Accommodation selectedAccommodation = getAccommodation(selectedArea, i);
            
            // If the selected accommodation is not null
            if (selectedAccommodation != null)
            {
               
            // Create cleaning status object to get status for each accommodation
            CleaningStatus requiresCleaning = selectedAccommodation.getCleaningStatus();

            
            // If object value = requires cleaning
            if (requiresCleaning == CleaningStatus.REQUIRES_CLEANING)
            {
                // Add one to the total amount of cleaning required
                numCleaning++;
            }
        }
        }
        // Then set the text field to the string value of total cleaning required
        totalReqCleaning.setText(String.valueOf(numCleaning));
    }

}