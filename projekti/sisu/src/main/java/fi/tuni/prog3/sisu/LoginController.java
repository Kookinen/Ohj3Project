package fi.tuni.prog3.sisu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;


public class LoginController implements Initializable{

    private static HashMap<String, Degree> degrees;
    private Stage stage = new Stage();

    @FXML
    TextField name = new TextField();
    @FXML
    TextField studentNumber = new TextField();
    @FXML
    private ComboBox<String> degreePicker = new ComboBox<>();
    


    
    /** 
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        GUITools.setUpDegreeBox(degreePicker, degrees);
        
    }

    @FXML
    public void loadStudentData(){
        

        try{
            Student student = SaveProgress.loadStudent();

            //? getDegree = null
            Controller.setStudent(student);

            Parent parent = loadFXMLsettings("/mainGUI.fxml");
            Scene scene = new Scene(parent);
            stage.setScene(scene);
        }
        catch (FileNotFoundException e){
        }
    }

    @FXML
    public void openRegister(){
        
        Parent parent = loadFXMLsettings("/registerGUI.fxml");
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        
    }

    @FXML
    public void closeProgram(){
        Platform.exit();
        System.exit(0);
    }

    @FXML void registerStudent(){
        
        String newName = name.getText();
        String newStudentNumber = studentNumber.getText();
        String newDegree = getPickedDegree();

        Student student = new Student(newName, newStudentNumber);
        student.setDegree(newDegree);

        Controller.setStudent(student);

        Parent parent = loadFXMLsettings("/mainGUI.fxml");
        Scene scene = new Scene(parent);
        
        stage.setScene(scene);
        stage.show();

    }


    
    /** 
     * @return String
     */
    @FXML
    public String getPickedDegree(){
        String s = new String();
        if(!degreePicker.getEditor().getText().isEmpty() && degrees.containsKey(degreePicker.getEditor().getText())){
            Degree degree = degrees.get(degreePicker.getEditor().getText());
            s = degree.getName();          
        }
        return s; 
    }

    
    /** 
     * @param fileName
     * @return Parent
     */
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


    
    /** 
     * @param newStage
     */
    public void setStage(Stage newStage){
        stage = newStage;
    }

    /** 
     * @param degrees
     */
    public static void setDegrees(HashMap<String, Degree> degrees){
        LoginController.degrees = degrees;
    }

    
    
}
