/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.sisu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class sisu extends Application{
    
    @Override
    public void start(Stage stage){
        GridPane grid = new GridPane();
        Scene login = new Scene(grid, 500, 500);
        grid.setHgap(5);
        grid.setVgap(5);
        
        Label logMessage = new Label("Log in using your credentials");
        logMessage.setMaxWidth(250);
        
        Label usernameLabel = new Label("Username:");
        TextField userName = new TextField();
        userName.setMaxWidth(150);
        
        Label studentNumberLabel = new Label("Student Number:");
        TextField studentNumber = new TextField();
        studentNumber.setMaxWidth(150);
        
        Button logButton = new Button("Log in");
        logButton.setPrefWidth(150);
        
        grid.add(logMessage, 0, 0, 2, 1);
        grid.add(usernameLabel, 0, 1);
        grid.add(userName, 1, 1);
        grid.add(studentNumberLabel, 0, 2);
        grid.add(studentNumber, 1, 2);
        grid.add(logButton, 1, 3);
        grid.setAlignment(Pos.CENTER);
        
        /*
          välilehtiä varten vbox
        */
        VBox vbox = new VBox();
        Scene mainScene = new Scene(vbox, 400, 400);
        
        logButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(!userName.getText().isEmpty() && !studentNumber.getText().isEmpty()){
                    stage.setScene(mainScene);
                    Student student = new Student(userName.getText(), studentNumber.getText());
                    mainWindow main = new mainWindow();
                    vbox.getChildren().add(main.getTabs());
                }
            }
        });
        
        
        stage.setScene(login);
        stage.setTitle("Sisu");
        stage.show();

    }
    
    public static void main(String args[]) {
        launch();
    }

}
