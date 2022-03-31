/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.calc;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Joni
 */
public class Calculator extends Application{

    
    @Override
    public void start(Stage stage){
        
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 250, 100);
        
        
        TextField input1 = new TextField();
        input1.setPrefWidth(100);
        input1.setId("fieldOp1");
        grid.add(input1, 1, 0);
        
        TextField input2 = new TextField();
        input2.setPrefWidth(100);
        input2.setId("fieldOp2");
        grid.add(input2, 1, 1);
        
        Label inputLabel1 = new Label("First operand:");
        
        inputLabel1.setId("labelOp1");
        grid.add(inputLabel1, 0, 0);
        
        Label inputLabel2 = new Label("Second operand:");
        
        inputLabel2.setId("labelOp2");
        grid.add(inputLabel2, 0, 1);
        
        HBox buttons = new HBox(5);
        grid.add(buttons, 0, 3, 2, 1);
        
        Button addButton = new Button("Add");
        addButton.setId("btnAdd");
        buttons.getChildren().add(addButton);
        
        Button subButton = new Button("Subtract");
        subButton.setId("btnSub");
        buttons.getChildren().add(subButton);
        
        Button mulButton = new Button("Multiply");
        mulButton.setId("btnMul");
        buttons.getChildren().add(mulButton);
        
        Button divButton = new Button("Divide");
        divButton.setId("btnDiv");
        buttons.getChildren().add(divButton);
        
        Label resTextLabel = new Label("Result:");
        resTextLabel.setPrefWidth(75);
        resTextLabel.setId("labelRes");
        grid.add(resTextLabel,0,4);
        
        Label resLabel = new Label();
        resLabel.setPrefWidth(50);
        resLabel.setId("fieldRes");
        resLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        resLabel.setStyle("-fx-border-color: black;");
        grid.add(resLabel,1,4);
        
        addButton.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                double a = Double.parseDouble(input1.getText());
                double b = Double.parseDouble(input2.getText());
                double r = a + b;
                resLabel.setText(String.format("%.1f", r));
            }
        });
        
        subButton.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               double a = Double.parseDouble(input1.getText());
               double b = Double.parseDouble(input2.getText());
               double r = a - b;
               resLabel.setText(String.format("%.1f", r));
           }
        });
        mulButton.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               double a = Double.parseDouble(input1.getText());
               double b = Double.parseDouble(input2.getText());
               double r = a * b;
               double remainder = r%b;
               
               if(remainder == 0){
                   resLabel.setText(String.format("%.1f", r));
               }
               else{
                   resLabel.setText(String.format("%.2f", r));
               }
               
           }
        });
        divButton.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               double a = Double.parseDouble(input1.getText());
               double b = Double.parseDouble(input2.getText());
               double r = a / b;
               resLabel.setText(String.format("%.1f", r));
           }
        });
        
        
        
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();
    }
    
    public static void main(String args[]) {
        launch();
    }
}
