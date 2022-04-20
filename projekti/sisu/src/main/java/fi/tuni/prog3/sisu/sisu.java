
package fi.tuni.prog3.sisu;

import com.google.gson.*;

import java.io.FileNotFoundException;
//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
import java.net.MalformedURLException;
//import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class sisu extends Application{

    
    public HashMap<String, Degree> degrees = new HashMap<>();
    
    @Override
    public void start(Stage stage){

        try{ 
            GetJsonData getJson_Degree = new GetJsonData(1, "");
            StringBuilder sb = getJson_Degree.getJsonDataFromURL();
            decodeJson(sb);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e2){
            e2.printStackTrace();
        }
        

        try{
            Image icon = GUITools.getImage("sisuTrans.PNG");
            stage.getIcons().add(icon);
            
        }
        catch (FileNotFoundException e){

        }

        GridPane grid = new GridPane();
        Scene login = new Scene(grid, 500, 500);
        grid.setHgap(5);
        grid.setVgap(5);
        
        Label logMessage = new Label("Log in using your credentials or register as a new user");
        logMessage.setMaxWidth(300);
        
        Label usernameLabel = new Label("Username:");
        TextField userName = new TextField();
        userName.setMaxWidth(150);
        
        Label studentNumberLabel = new Label("Student Number:");
        TextField studentNumber = new TextField();
        studentNumber.setMaxWidth(150);
        
        Label degreeLabel = new Label("Select degree:");
        ComboBox degreeBox = new ComboBox();
        
        
        
        Button logButton = new Button("Log in");
        logButton.setPrefWidth(150);
        
        Button regButton = new Button("Register");
        regButton.setPrefWidth(150);
        
        Button startButton = new Button("Register");
        startButton.setPrefWidth(150);
        
        grid.add(logMessage, 0, 0, 2, 1);
        grid.add(regButton, 0,1);
        grid.add(logButton, 0, 2);
        grid.setAlignment(Pos.CENTER);
        
        /*
          välilehtiä varten vbox
        */
        //Group root = new Group();
        VBox vbox = new VBox();
        


        
        
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Student student = null;
                boolean studentFound = false;
                if(!userName.getText().isEmpty() && !studentNumber.getText().isEmpty()){
                    if(startButton.getText().equals("Register") && !degreeBox.getEditor().getText().isEmpty()){
                        student = new Student(userName.getText(), studentNumber.getText());
                        studentFound = true;
                    }
                    else{
                        try {
                            student = SaveProgress.loadStudent();
                            studentFound = true;
                        } catch (FileNotFoundException ex) {}
                    }
                    //stage.setMaximized(true);
                    //stage.setFullScreen(true);
                    //stage.setFullScreenExitHint("Fullscreen-tilasta pääsee pois painamalla ESC!");
                    if(studentFound){                    
                        Controller.setStudent(student);
                        Controller.setDegree(degrees.get(degreeBox.getEditor().getText()));
                        Parent root = new Parent(){};
                        try{
                            root = FXMLLoader.load(this.getClass().getResource("/test.fxml"));
                            System.out.print("UI-tiedosto löytyi!\n");

                        }
                        catch (IOException e3){
                            System.out.print("VIRHE TIEDOSTON KÄSITTELYSSÄ! "+e3.getCause());
                        }
                        catch (NullPointerException e4){
                            System.out.print("UI-tiedostoa ei löydy!\n");
                        }

                        Scene mainScene = new Scene(root);
                        stage.setScene(mainScene);
                    }
                }
            }
        });
        regButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                grid.getChildren().clear();
                
                
                grid.add(usernameLabel, 0, 1);
                grid.add(userName, 1, 1);
                grid.add(studentNumberLabel, 0, 2);
                grid.add(studentNumber, 1, 2);
                grid.add(startButton, 1, 4);
                grid.add(degreeLabel, 0, 3);
                grid.add(degreeBox, 1, 3);
                GUITools.setUpDegreeBox(degreeBox, degrees);
                
            }
        });
        logButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                grid.getChildren().clear();
                
                startButton.setText("Log in");
                
                grid.add(usernameLabel, 0, 1);
                grid.add(userName, 1, 1);
                grid.add(studentNumberLabel, 0, 2);
                grid.add(studentNumber, 1, 2);
                grid.add(startButton, 1, 3);

                //Student jsonista tietojen haku!!!
                
            }
        });

        

        stage.setScene(login);
        stage.setTitle("Sisu");
        stage.show();

        

    }
    
    public static void main(String args[]) {
        launch();
    }
    
    private void decodeJson(StringBuilder sb) {
        JsonObject obj = JsonParser.parseString(sb.toString()).getAsJsonObject();
        JsonArray arr = obj.getAsJsonArray("searchResults");
        Iterator<JsonElement> it = arr.iterator();
        while(it.hasNext()){
            JsonObject jObject = it.next().getAsJsonObject();
            JsonElement id = jObject.get("id");
            JsonElement code = jObject.get("code");
            JsonElement lang = jObject.get("lang");
            JsonElement groupId = jObject.get("groupId");
            JsonElement name = jObject.get("name");
            JsonElement minCredit = jObject.get("credits").getAsJsonObject().get("min");
            Degree deg = new Degree(id.getAsString(), code.getAsString(),
                    lang.getAsString(),groupId.getAsString(), name.getAsString(), minCredit.getAsInt());
            degrees.put(name.getAsString(), deg);

            
        }
        Controller.setDegrees(degrees);
        
    }
}
