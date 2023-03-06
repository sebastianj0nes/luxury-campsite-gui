/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jameslear
 */

// https://code.makery.ch/blog/javafx-8-event-handling-examples/
// https://docs.oracle.com/javafx/2/get_started/fxml_tutorial.htm
public class AccommodationSystemGUI extends Application 
{
    @Override
    public void start(Stage primaryStage)
            throws IOException
    {   
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        
        primaryStage.setTitle("Accommodation System");
        Scene accommodationSystemScene = new Scene(loader.load());
        
        primaryStage.setScene(accommodationSystemScene);
        primaryStage.show(); 

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }   
    
}
