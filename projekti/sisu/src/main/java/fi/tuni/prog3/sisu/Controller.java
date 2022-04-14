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
        Degree deg = degrees.get("otm-d729cfc3-97ad-467f-86b7-b6729c496c82");
        TreeItem<String> rootItem = new TreeItem<>(deg.getName());
        printAll(deg.getModules(), rootItem);
        
        mainView.setRoot(rootItem);
    }

    @FXML
    public void selectItem(){

    }
    public static void setDegrees(HashMap degrees){
        Controller.degrees = degrees;
    }
    
    private void printAll(HashMap<String, Module> modules, TreeItem root){
        TreeItem<String> moduleItem;
        TreeItem<String> courseItem;
        //käydään kaikki modulet läpi
        for(Module m:modules.values()){
            moduleItem = new TreeItem<>(m.getName());
            root.getChildren().add(moduleItem);
            HashMap<String, Course> cors = m.getCourses();
            //käydään modulen alaiset kurssit ( jos on )
            for(Course c:cors.values()){
                courseItem = new TreeItem<>(c.getName()+ " " + c.getTargetCredits());
                //lisätään kurssi modulen alle
                moduleItem.getChildren().add(courseItem);
            }
            HashMap<String, Module> mods = m.getModules();
            if(!mods.isEmpty()){
                //modulesta uusi root kun kutsutaan uudestaan
                printAll(mods, moduleItem);
            }
        }
    }
    
}
