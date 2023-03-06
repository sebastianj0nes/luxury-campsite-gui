/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ArrayList;
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
import model.GuestBooking;
import model.Area;

/**
 * FXML Controller class
 *
 * @author Ben
 */
public class GUIController implements Initializable {
    
    private ObservableList<Area> areaData = FXCollections.observableArrayList();
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
    private ChoiceBox<?> cleaningStatus;
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
                GuestBooking guestBooking = accommodation.getGuestBooking();
                int noGuests = guestBooking.getNumberGuests();
                if (noGuests > 0)
                {
                    numberGuests = Integer.toString(noGuests);
                }
                
                boolean isBreakfastRequired = guestBooking.isBreakfastRequired();
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
                    accommodation.getCleaningStatus(),
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
    private void onTableClicked(MouseEvent event) 
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
            
            // Set the text field
            accomNum.setText(String.valueOf(accommNo));
            
            
        }
    }
    
    private void cbAreaOnAction(ActionEvent event)
    {
        Area area = choiceBox.getValue();
        
        populateTable(area);
    }

    @FXML
    private void OnTableClicked(MouseEvent event) {
    }
    
    
}