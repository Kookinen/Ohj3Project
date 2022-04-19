package fi.tuni.prog3.sisu;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/*
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
*/

public class Controller implements Initializable{
    
    private static HashMap<String, Degree> degrees;
    
    @FXML
    private TreeView<String> mainView = new TreeView<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        //otettu vain yksittäinen degree.
        //TODO: degree-lista josta valitaan mieluinen tai hakusysteemi
        TreeItem rootItem = GUITools.initializeTree(degrees);
        mainView.setRoot(rootItem);
    }

    @FXML
    public void selectItem(){

    }
    public static void setDegrees(HashMap degrees){
        Controller.degrees = degrees;
    }
}
