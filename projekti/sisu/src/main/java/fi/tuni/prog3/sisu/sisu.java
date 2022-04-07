/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.sisu;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
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

    //Tämä rivi testaa tuleeko kaksi branchia...
    
    public HashMap<String, Degree> degrees = new HashMap<String, Degree>();
    
    @Override
    public void start(Stage stage){
        try{
            getJsonData();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
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

    private void getJsonData() throws MalformedURLException, IOException{
        URL url = new URL("https://sis-tuni.funidata.fi/kori/api/module-search?"
                + "curriculumPeriodId=uta-lvv-2021&universityId=tuni-university"
                + "-root-id&moduleType=DegreeProgramme&limit=1000");
        HttpURLConnection c = (HttpURLConnection) url.openConnection();
        String line;
        StringBuilder sb = new StringBuilder();
        c.setRequestMethod("GET");
        c.setConnectTimeout(5000);
        c.setReadTimeout(5000);
        if(c.getResponseCode()<300){
            BufferedReader read = new BufferedReader(new InputStreamReader(c.getInputStream()));
            while((line = read.readLine()) != null){
                sb.append(line);
            }
            read.close();
        }
        else{
            System.out.println("Not works :(");
        }
        c.disconnect();
        decodeJson(sb);
    }

    private void decodeJson(StringBuilder sb) {
        JsonObject obj = JsonParser.parseString(sb.toString()).getAsJsonObject();
        JsonArray arr = obj.getAsJsonArray("searchResults");
        Iterator<JsonElement> it = arr.iterator();
        while(it.hasNext()){
            JsonElement id = it.next().getAsJsonObject().get("id");
            JsonElement code = it.next().getAsJsonObject().get("code");
            JsonElement lang = it.next().getAsJsonObject().get("lang");
            JsonElement name = it.next().getAsJsonObject().get("name");
            Degree deg = new Degree(id.getAsString(), code.getAsString(),
                    lang.getAsString(), name.getAsString());
            degrees.put(name.getAsString(), deg);

            
        }
                
    }

}
