
package fi.tuni.prog3.sisu;

import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
//import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class sisu extends Application{

    
    public HashMap<String, Degree> degrees = new HashMap<>();
    
    
    //private static String fileName;
    
    @Override
    public void start(Stage stage){

        try{ 

            //Haetaan data API:sta
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

         //Ikoni ikkunaan
         try{
            Image icon = GUITools.getImage("sisuTrans.PNG");
            stage.getIcons().add(icon);
            
        }
        catch (FileNotFoundException e){

        }
        
        Parent parent = new Parent() {};
        FXMLLoader loader = new FXMLLoader();

        loader = new FXMLLoader(getClass().getResource("/startupGUI.fxml"));
        
        try{
            loader = new FXMLLoader(getClass().getResource("/startupGUI.fxml"));
            parent = loader.load();
        }
        catch(IOException e3){

        }


        LoginController controller = loader.getController();
        controller.setParent(parent);
        controller.setStage(stage);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        
        //Parent mainWindow = loadFXMLsettings("/mainGUI.fxml");
        //Parent registerWindow = loadFXMLsettings("/registerGUI.fxml");
        
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
        LoginController.setDegrees(degrees);
        
    }
}
