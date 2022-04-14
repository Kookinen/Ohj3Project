package fi.tuni.prog3.sisu;

import java.net.URL;
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

    @FXML
    private TreeView<String> mainView = new TreeView<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        TreeItem<String> rootItem = new TreeItem<>("All and everything");
        String s = "test";

        TreeItem<String> testItem = new TreeItem<>("TEST");

        rootItem.getChildren().add(testItem);
        
        TreeItem<String> item = new TreeItem<>();
        for(int i = 0;i<10;i++){
            String ii = Integer.toString(i);
            item = new TreeItem<>(s+ii);
            rootItem.getChildren().add(item);
        }

        

        mainView.setRoot(rootItem);
    }

    @FXML
    public void selectItem(){

    }
}
