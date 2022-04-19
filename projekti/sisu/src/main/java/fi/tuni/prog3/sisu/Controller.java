package fi.tuni.prog3.sisu;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/*
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
*/

public class Controller implements Initializable{
    
    private static HashMap<String, Degree> degrees;
    private static Student student;
    
    @FXML
    private TreeView<String> mainView = new TreeView<>();
    @FXML
    private TextFlow courseInfo = new TextFlow();
    @FXML
    private Text studentName = new Text();
    @FXML
    private Text studentNumber = new Text();
    @FXML
    private Button saveButton = new Button();
    @FXML
    private TextField searchBar = new TextField();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        //otettu vain yksittäinen degree.
        //TODO: degree-lista josta valitaan mieluinen tai hakusysteemi
        TreeItem rootItem = GUITools.initializeTree(degrees);
        mainView.setRoot(rootItem);

        studentNumber.setText(student.getName());
        studentName.setText(student.getNumber());

        try {
            SaveProgress.saveStudent(student);

        } catch (IOException ex) {
            System.out.print("Couldn't save progress!");
        }
    }

    @FXML
    public void selectItem(){
        TreeItem<String> item = mainView.getSelectionModel().getSelectedItem();
        System.out.println(item.getValue());

        /*EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
               System.out.println("Hello World");  
            } 
        }; 

        mainView.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);*/
    }

  
     //Adding event Filter 

    public static void setDegrees(HashMap degrees){
        Controller.degrees = degrees;
    }

    public static void setStudent(Student student){
        Controller.student = student;
    }
}
