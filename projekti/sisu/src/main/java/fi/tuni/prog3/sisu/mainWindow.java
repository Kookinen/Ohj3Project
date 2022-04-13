package fi.tuni.prog3.sisu;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import javafx.animation.Animation;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.animation.RotateTransition;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;

//TODO: Rakenne menee näin:
// node -> pane -> VBOX -> tab -> scene
public class mainWindow {
    TabPane tabs;
    String searchId = "";
    public mainWindow(HashMap<String, Degree> degrees){

        try{
        
        TabPane tabs = new TabPane();
        Label testi = new Label("testitestitesti");
        Tab studentInfo = new Tab("student", testi);
        Tab studies = new Tab("Studies structure", new Label("testitesti"));
        
        //InputStream stream = sisu.class.getResourceAsStream("/sisu.PNG");
        InputStream stream = new FileInputStream("sisu.PNG");
        
        //Hakutoiminto testinä
        var group = new FlowPane();
        Button hakuNappi = new Button();
        hakuNappi.setText("Anna ID ja klikkaa");
        

        TextField input = new TextField();
        input.setPrefWidth(150);

        TextField output = new TextField();
        output.setPrefWidth(500);
        
        //Otetaan käyttäjän syöte talteen
        hakuNappi.setOnAction((event) -> {
            searchId = input.getText();
            String name = degrees.get(searchId).getName();
            degrees.get(searchId).getModules();
            String id = degrees.get(searchId).moduleGroupIds.keySet().toArray()[0].toString();
            String module = degrees.get(searchId).moduleGroupIds.get(id).getName();

            output.setText(name+ " " + module);
        });

        
        //"Rivinvaihto flowpaneen"
        
        
        group.getChildren().add(hakuNappi);
        group.getChildren().add(input);
        group.getChildren().add(output);
        studies.setContent(group);

        Image image = new Image(stream);
        ImageView imageView = new ImageView();

        imageView.setImage(image);
        
        //Kuvan pyöriminen
        RotateTransition rotateTransition = new RotateTransition(); 
        rotateTransition.setDuration(Duration.millis(1000));  
        rotateTransition.setNode(imageView);       
        rotateTransition.setByAngle(360); 
        rotateTransition.setCycleCount(50);  
        //rotateTransition.setAutoReverse(false); 
        rotateTransition.play();

        
        //Toisen ikkunan sisältö
        StackPane test = new StackPane();
        test.getChildren().add(imageView);

        studentInfo.setContent(test);
        

        tabs.getTabs().addAll(studentInfo, studies);
        this.tabs = tabs;

        }

        catch (FileNotFoundException e){
            System.out.println("Invalid file!");
            System.out.println();
            System.out.println();
            

        }
    }
    public TabPane getTabs(){
        return tabs;
    }
    
}
