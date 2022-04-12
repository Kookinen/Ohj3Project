
package fi.tuni.prog3.sisu;

import com.google.gson.*;
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        Scene mainScene = new Scene(vbox, 500, 500);
        
        logButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(!userName.getText().isEmpty() && !studentNumber.getText().isEmpty()){
                    stage.setScene(mainScene);
                    Student student = new Student(userName.getText(), studentNumber.getText());
                    mainWindow main = new mainWindow(degrees);
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
            //TODO: Maatuskan rakennus
            //TODO: moduleGroupId:t talteen että saa maatuskan sisällön kuntoon
            Degree deg = new Degree(id.getAsString(), code.getAsString(),
                    lang.getAsString(),groupId.getAsString(), name.getAsString(), minCredit.getAsInt());
            degrees.put(id.getAsString(), deg);

            
        }        
    }
}
