package fi.tuni.prog3.sisu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class LoginController implements Initializable{

    private static HashMap<String, Degree> degrees;
    private Stage stage = new Stage();
    private Parent startParent = new Parent() {};

    @FXML
    Button register = new Button();
    @FXML
    Button load = new Button();
    @FXML
    static Text errorMessage = new Text();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        errorMessage.setVisible(false);

         //Ikoni ikkunaan
         try{
            Image icon = GUITools.getImage("sisuTrans.PNG");
            stage.getIcons().add(icon);
            
        }
        catch (FileNotFoundException e){

        }

        Scene scene = new Scene(startParent);
        stage.setScene(scene);
        
        //Parent mainWindow = loadFXMLsettings("/mainGUI.fxml");
        //Parent registerWindow = loadFXMLsettings("/registerGUI.fxml");
        
        stage.setTitle("Sisu");
        stage.show();

    }

    @FXML
    public void loadStudentData(){
        try{
            Controller.setStudent(SaveProgress.loadStudent());
        }
        catch (FileNotFoundException e){
            errorMessage.setText("Tiedostoa ei löytynyt");
        }
    }


    public Parent loadFXMLsettings(String fileName){

        Parent mainWindow = new Parent(){};
        try{
            mainWindow = FXMLLoader.load(this.getClass().getResource(fileName));
            System.out.print("UI-tiedosto löytyi!\n");

        }
        catch (IOException e3){
            System.out.print("VIRHE TIEDOSTON KÄSITTELYSSÄ! "+e3.getCause());
        }
        catch (NullPointerException e4){
            System.out.print("UI-tiedostoa ei löydy!\n");
        }

        return mainWindow;
    }


    public void setStage(Stage newStage){
        stage = newStage;
    }

    public void setParent(Parent newParent){
        startParent = newParent;
    }

    public static void setDegrees(HashMap<String, Degree> degrees){
        errorMessage.setVisible(true);
        LoginController.degrees = degrees;
    }
    
}
