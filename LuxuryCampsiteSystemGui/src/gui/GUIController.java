/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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

/**
 * FXML Controller class
 *
 * @author Ben
 */
public class GUIController implements Initializable {
    
    private ObservableList<Area> areaData = FXCollections.observableArrayList();
    private ObservableList<CleaningStatus> cleaningStatusData = FXCollections.observableArrayList();
    private ObservableList<AccommodationRow> tableData = FXCollections.observableArrayList();
    private LuxuryCampSiteSystem accommodationSystem = null;

    @FXML
    private TableView<AccommodationRow> myHTable;
    /*
    private TableColumn<AccommodationRow, String> myHTableNumber;
    private TableColumn<AccommodationRow, String> myHTableAccomType;
    private TableColumn<AccommodationRow, String> myHTableOccupancy;
    private TableColumn<AccommodationRow, String> myHTableAvail;
    private TableColumn<AccommodationRow, String> myHTableStatus;
    private TableColumn<AccommodationRow, String> myHTableGuests;
    private TableColumn<AccommodationRow, String> myHTableBreakfast;
    private TextField hAccomNum;
    private TextField hFirstName;
    */

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

        //INITIALIZE THE ACCOMMODATION SYSTEM AND LOAD DUMMY DATA
        // Storing accommodation system in a state variable for reusability
        LuxuryCampSiteSystem accommodationSystem = LuxuryCampSiteSystem.getInstance();
        // accommodationSystem.initializeDummyData();
        
        //Storing accommodation system
        setAccommodationSystem(accommodationSystem);
        
        ArrayList<Area> areas = accommodationSystem.getAreas();
        
        for (int i = 0; i<areas.size(); i++)
        {
            areaData.add(areas.get(i));
        }
        
        choiceBox.setItems(areaData);
        choiceBox.setValue(areaData.get(0));
        
        cleaningStatusData.add(CleaningStatus.CLEAN);
        cleaningStatusData.add(CleaningStatus.REQUIRES_CLEANING);

        cleaningStatus.setItems(cleaningStatusData);
        cleaningStatus.setValue(cleaningStatusData.get(0));
        
        //POPULATE TABLE WITH DUMMY DATA
        populateTable(areaData.get(0));
    
    }
    
        
       private void setAccommodationSystem(LuxuryCampSiteSystem accommodationSystem)
       {
           this.accommodationSystem = accommodationSystem;
       }
       
       private LuxuryCampSiteSystem getAccommodationSystem()
       {
           return this.accommodationSystem;
       }
        
       
       private void populateTable(Area area)
       {
           System.out.println("populateTableCalled");
           
           // We need to now iterate or loop through the accommodation.
           // For each accommodation create a new row within the table
           
           ArrayList<Accommodation> accommodations = area.getAccommodations();

        // Initially clear the table
        tableData.clear();

        for (Accommodation accommodation : accommodations) 
        {
            // Now retrieve each accommodation and transfer into the AccommodationRow,
            // to add to the table.
            String occupancy = "Unoccupied";
            if (accommodation.isOccupied())
            {
                occupancy = "Occupied";
            }
            
            String numberGuests = "";
            String breakfastRequired = "No";
            
            if (accommodation.hasGuestBooking())
            {
                Booking guestBooking = accommodation.getGuestBooking();
                int noGuests = guestBooking.getNumberGuests();
                if (noGuests > 0)
                {
                    numberGuests = Integer.toString(noGuests);
                }
                
                boolean isBreakfastRequired = guestBooking.getRequiresBreakfast();
                if (isBreakfastRequired)
                {
                    breakfastRequired = "Yes";
                }
            }
            
            String availability = "Unavailable";
            if (accommodation.isAvailable())
            {
                availability = "Available";
            }
            
            tableData.add(new AccommodationRow(String.valueOf(accommodation.getAccommodationNo()),
                    accommodation.getType(),
                    occupancy,
                    availability,
                    accommodation.getCleaningStatus().getDescription(),
                    numberGuests,
                    breakfastRequired));
        }

        // Set the table with the new data to show
        myHTable.setItems(tableData);
       }
    // Based on an accommodation number, find the associated Accommodation object.
    private Accommodation getAccommmdation(Area area, int accommNo)
    {
        ArrayList<Accommodation> accommodations = area.getAccommodations();
        
        Accommodation foundAccommodation = null;

        for (Accommodation accommodation : accommodations) 
        {
            if (accommodation.getAccommodationNo() == accommNo)
            {
                // Found the object
                foundAccommodation = accommodation;
                break;
            }
        }
        
        return foundAccommodation;
    }

    // This was generated from XML, right click on the FXML and select Make Controller.
    /*
    @FXML
    private void OnTableClicked(MouseEvent event) 
    {
        // Reset the fields
        firstName.setText("");
        surname.setText("");
        
        AccommodationRow accommodationRow = myHTable.getSelectionModel().getSelectedItem();
        // int selectedIndex = myHTable.getSelectionModel().getSelectedIndex();
        
        if (accommodationRow != null) 
        {
            // Retrieve the accommodation number of the selected row
            // and find the associated accommodation object in the system.
            // Then set the text fields with the values.
            System.out.println(accommodationRow.getNumber());
            
            // Retrieve the values from the Accommodation Row for the selected row
            // Example below:
            int accommNo = Integer.valueOf(accommodationRow.getNumber());
            
            // Find the associated accommodation object based on the unique
            // accommodation number
            /* TEMP REMOVED
            Accommodation selectedAccommodation = getAccommmdation(accommNo);
            
            if (selectedAccommodation.hasGuestBooking())
            {
                GuestBooking associatedGuestBooking = selectedAccommodation.getGuestBooking();
                String firstName = associatedGuestBooking.getFirstName();
                
                // Set the text field
                hFirstName.setText(firstName);
                
            }
*/
 /*           
            // Set the text field
            accomNum.setText(String.valueOf(accommNo));
            
            
        }
    }
*/
    
    private void cbAreaOnAction(ActionEvent event)
    {
        Area area = choiceBox.getValue();
        
        populateTable(area);
    }

    private void cbCleaningStatusOnAction(ActionEvent event)
    {       
        Area area = choiceBox.getValue();

        // Retrieve the values from the Accommodation Row for the selected row
        // Example below:
        AccommodationRow accommodationRow = myHTable.getSelectionModel().getSelectedItem();
        if (accommodationRow != null)
        {

            int accommNo = Integer.valueOf(accommodationRow.getNumber());
            // Find the associated accommodation object based on the unique
            // accommodation number           
            Accommodation selectedAccommodation = getAccommmdation(area, accommNo);

            selectedAccommodation.setCleaningStatus(cleaningStatus.getValue());

            populateTable(area);
        }
    }
//    @FXML
//    private void OnTableClicked(MouseEvent event) {
//        
//        
//        
//        
//    }

    @FXML
    private void OnTableClicked(MouseEvent event) 
    {

        // Reset the fields
        firstName.setText("");
        surname.setText("");
        telNum.setText("");

        numOfGuests.setText("");
        checkInDate.setText("");
        numOfNights.setText("");
        //breakfastTick.setText("");

        accomType.setText("");
        accomNum.setText("");
        accomAccommodates.setText("");
        costPerNight.setText("");
        areaDesc.setText("");
        
        breakfastTick.setSelected(false);

        AccommodationRow accommodationRow = myHTable.getSelectionModel().getSelectedItem();
        // int selectedIndex = myHTable.getSelectionModel().getSelectedIndex();
        
        if (accommodationRow != null) 
        {
            // Retrieve the accommodation number of the selected row
            // and find the associated accommodation object in the system.
            // Then set the text fields with the values.
            System.out.println(accommodationRow.getNumber());
            
            Area area = choiceBox.getValue();
            
            // Retrieve the values from the Accommodation Row for the selected row
            // Example below:
            int accommNo = Integer.valueOf(accommodationRow.getNumber());
            // Find the associated accommodation object based on the unique
            // accommodation number           
            Accommodation selectedAccommodation = getAccommmdation(area, accommNo);

            // Display the general accommodation info
            accomType.setText(selectedAccommodation.getType());
            accomNum.setText(String.valueOf(accommNo));
            accomAccommodates.setText(String.valueOf(selectedAccommodation.getCapacity()));
            costPerNight.setText(String.valueOf(selectedAccommodation.getPrice()));
            areaDesc.setText(area.getDescription());
            
            CleaningStatus cleaningStatusVal = selectedAccommodation.getCleaningStatus();
            
            if (cleaningStatusVal == CleaningStatus.CLEAN)
            {
                cleaningStatus.setValue(cleaningStatusData.get(0));
            }
            else
            {
                cleaningStatus.setValue(cleaningStatusData.get(1));
            }
            
            // If we have a booking then display the bookling info
            if (selectedAccommodation.hasGuestBooking())
            {
                Booking associatedGuestBooking = selectedAccommodation.getGuestBooking();
                Guest guest = associatedGuestBooking.getGuest();
                
                String bookingFirstName = guest.getFirstName();
                String bookingLastName = guest.getLastName();
                String telephoneNumber = guest.getTelephoneNumber();
                
                String numberOfGuests = String.valueOf(associatedGuestBooking.getNumberGuests());
                String formattedDate = associatedGuestBooking.getCheckInDay().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
                boolean requiresBreakfast = associatedGuestBooking.getRequiresBreakfast();
                String numberNights = String.valueOf(associatedGuestBooking.getDuration());
                
                // Set the text field
             // Reset the fields
                firstName.setText(bookingFirstName);
                surname.setText(bookingLastName);
                telNum.setText(telephoneNumber);

                numOfGuests.setText(numberOfGuests);
                checkInDate.setText(formattedDate);
                numOfNights.setText(numberNights);
                // breakfastTick.setText(numberNights);
                breakfastTick.setSelected(requiresBreakfast);
                
                
            }
 /*           
            // Set the text field
            accomNum.setText(String.valueOf(accommNo));
            
            
            
        }
    }
            
            // Find the associated accommodation object based on the unique
            // accommodation number
            /* TEMP REMOVED
            Accommodation selectedAccommodation = getAccommmdation(accommNo);
            
            if (selectedAccommodation.hasGuestBooking())
            {
                GuestBooking associatedGuestBooking = selectedAccommodation.getGuestBooking();
                String firstName = associatedGuestBooking.getFirstName();
                
                // Set the text field
                hFirstName.setText(firstName);
                
            }
*/
 /*           
            // Set the text field
            accomNum.setText(String.valueOf(accommNo));
            
            
        }
    }
*/
        }
    } 

    @FXML
    private void onCheckin(ActionEvent event) 
    {
        String firstNameVal = firstName.getText();
        String surnameVal = surname.getText();
        String telNumVal = telNum.getText();
        String numofGuestsVal = numOfGuests.getText();
        String checkInDateVal = checkInDate.getText();
        String numOfNightsVal = numOfNights.getText();
        boolean breakfastRequired = breakfastTick.isSelected();
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate checkinDate = LocalDate.parse(checkInDateVal, dateTimeFormatter);
        
        // Constructor
        Guest guest = new Guest(firstNameVal, surnameVal, telNumVal);
        Booking booking = new Booking(breakfastRequired,
                            Integer.valueOf(numofGuestsVal),
                            checkinDate,
                            Integer.valueOf(numOfNightsVal),
                            guest);
        
        AccommodationRow accommodationRow = myHTable.getSelectionModel().getSelectedItem();
        
        //if (accommodationRow != null) 
        //{
        // Retrieve the accommodation number of the selected row
        // and find the associated accommodation object in the system.
        // Then set the text fields with the values.
            
        Area area = choiceBox.getValue();

        // Retrieve the values from the Accommodation Row for the selected row
        // Example below:
        int accommNo = Integer.valueOf(accommodationRow.getNumber());
        // Find the associated accommodation object based on the unique
        // accommodation number           
        Accommodation selectedAccommodation = getAccommmdation(area, accommNo);
        
        selectedAccommodation.checkin(booking);

        populateTable(area);
        
        
    }

    @FXML
    private void onCheckout(ActionEvent event) 
    {
         AccommodationRow accommodationRow = myHTable.getSelectionModel().getSelectedItem();
        
        //if (accommodationRow != null) 
        //{
        // Retrieve the accommodation number of the selected row
        // and find the associated accommodation object in the system.
        // Then set the text fields with the values.
            
        Area area = choiceBox.getValue();

        // Retrieve the values from the Accommodation Row for the selected row
        // Example below:
        int accommNo = Integer.valueOf(accommodationRow.getNumber());
        // Find the associated accommodation object based on the unique
        // accommodation number           
        Accommodation selectedAccommodation = getAccommmdation(area, accommNo);
        
        selectedAccommodation.setCleaningStatus(CleaningStatus.REQUIRES_CLEANING);
        
        selectedAccommodation.checkOut();
        
        populateTable(area);
        
        firstName.setText("");
        surname.setText("");
        telNum.setText("");

        numOfGuests.setText("");
        checkInDate.setText("");
        numOfNights.setText("");
        
        breakfastTick.setSelected(false);
        
        cleaningStatus.setValue(cleaningStatusData.get(1));
        
    }
}