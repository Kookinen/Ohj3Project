/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.sisu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class sisu extends Application{
    
    @Override
    public void start(Stage stage){
        BorderPane Bp = new BorderPane();
        Scene scene = new Scene(Bp, 500, 500);
        VBox logins = new VBox(5);
        
        Label logMessage = new Label("Log in using your credentials");
        logMessage.setMaxWidth(250);
        
        
        TextField userName = new TextField("Username");
        userName.setMaxWidth(150);
        
        TextField studentNumber = new TextField("Student number");
        studentNumber.setMaxWidth(150);
        
        Button logButton = new Button("Log in");
        logButton.setPrefWidth(150);
        
        logins.getChildren().addAll(logMessage, userName, studentNumber, logButton);
        
        Bp.setCenter(logins);
        stage.setScene(scene);
        stage.setTitle("Sisu");
        stage.show();

    }
    
    public static void main(String args[]) {
        launch();
    }

}
