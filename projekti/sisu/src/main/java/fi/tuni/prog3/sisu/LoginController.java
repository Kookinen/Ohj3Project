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

/**
 * Controls the start and registration windows. Responsible for GUI
 * functionality.
 * Controls startupGUI.fxml and registerGUI.fxml.
 * 
 * @author Joni Koskinen
 * @author Julius Juutilainen
 */
public class LoginController implements Initializable {

    private static HashMap<String, Degree> degrees;
    private Stage stage = new Stage();

    @FXML
    TextField name = new TextField();
    @FXML
    TextField studentNumber = new TextField();
    @FXML
    private ComboBox<String> degreePicker = new ComboBox<>();

    /**
     * Initializes the UI and performs necessary actions.
     * 
     * @param arg0 unused.
     * @param arg1 unused.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        GUITools.setUpDegreeBox(degreePicker, degrees);

    }

    /**
     * Loads student data from file.
     * Uses method from SaveProgress class.
     * 
     * @throws FileNotFoundException if file is not found.
     */
    @FXML
    public void loadStudentData() {

        try {
            Student student = SaveProgress.loadStudent();

            Controller.setStudent(student);

            Parent parent = loadFXMLsettings("/mainGUI.fxml");
            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (FileNotFoundException e) {

        }
    }

    /**
     * Opens the registration window.
     */
    @FXML
    public void openRegister() {

        Parent parent = loadFXMLsettings("/registerGUI.fxml");

        Scene scene = new Scene(parent);
        stage.setScene(scene);

    }

    /**
     * Closes the program.
     */
    @FXML
    public void closeProgram() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Registers new student and opens the main window.
     * Initialized new Student object and passes it on to main Controller.
     */
    @FXML
    void registerStudent() {

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
     * TODO: dokumentoi
     * 
     * @return String
     */
    @FXML
    public String getPickedDegree() {
        String s = new String();
        if (!degreePicker.getEditor().getText().isEmpty() && degrees.containsKey(degreePicker.getEditor().getText())) {
            Degree degree = degrees.get(degreePicker.getEditor().getText());
            s = degree.getName();
        }
        return s;
    }

    /**
     * Loads a FXML-file and sets it to new Parent.
     * 
     * @param fileName path to file.
     * @return Parent containin the FXML formatting.
     */
    public Parent loadFXMLsettings(String fileName) {

        Parent mainWindow = new Parent() {
        };
        try {
            mainWindow = FXMLLoader.load(this.getClass().getResource(fileName));
            System.out.print("UI-tiedosto löytyi!\n");

        } catch (IOException e3) {
            System.out.print("VIRHE TIEDOSTON KÄSITTELYSSÄ! " + e3.getCause());
        } catch (NullPointerException e4) {
            System.out.print("UI-tiedostoa ei löydy!\n");
        }

        return mainWindow;
    }

    /**
     * Sets the stage for the controller. Is used to get the sisu.java stage for the
     * controller to use.
     * 
     * @param newStage stage to be set as the controller stage.
     */
    public void setStage(Stage newStage) {
        stage = newStage;
    }

    /**
     * Sets the HashMap of degrees for the controller.
     * 
     * @param degrees HashMap containing Degree-objects.
     */
    public static void setDegrees(HashMap<String, Degree> degrees) {
        LoginController.degrees = degrees;
    }

}
