package fi.tuni.prog3.sisu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import javafx.util.Duration;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUITools {
    
    public GUITools(){

    }

    public static Image getImage(String filename) throws FileNotFoundException{

        InputStream stream = new FileInputStream(filename);
        Image image = new Image(stream);

        return image;
    }

    public static Node getImageAsNode(String filename) throws FileNotFoundException{
        
        Image image = getImage(filename);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        
        return imageView;
    }

    public RotateTransition spin(Node node){
        
        RotateTransition rotateTransition = new RotateTransition(); 
        rotateTransition.setDuration(Duration.millis(1000));  
        rotateTransition.setNode(node);       
        rotateTransition.setByAngle(360); 
        rotateTransition.setCycleCount(50);  
        //rotateTransition.setAutoReverse(false); 

        return rotateTransition;

    }
    
    public static TreeItem initializeTree(HashMap<String, Degree> degrees){
        Degree deg = degrees.get("otm-d729cfc3-97ad-467f-86b7-b6729c496c82");
        TreeItem<String> rootItem = new TreeItem<>(deg.getName());
        printTree(deg.getModules(), rootItem);
        return rootItem;
    }
    
    private static void printTree(HashMap<String, Module> modules, TreeItem root){
        TreeItem<String> moduleItem;
        TreeItem<String> courseItem;
        //käydään kaikki modulet läpi
        for(Module m:modules.values()){
            moduleItem = new TreeItem<>(m.getName()+ " " + m.getTargetCredits()+ "op");
            root.getChildren().add(moduleItem);
            HashMap<String, Course> cors = m.getCourses();
            //käydään modulen alaiset kurssit ( jos on )
            for(Course c:cors.values()){
                courseItem = new TreeItem<>(c.getName()+ " " + c.getTargetCredits()+"op");
                //lisätään kurssi modulen alle
                moduleItem.getChildren().add(courseItem);
            }
            HashMap<String, Module> mods = m.getModules();
            if(!mods.isEmpty()){
                //modulesta uusi root kun kutsutaan uudestaan
                printTree(mods, moduleItem);
            }
        }
    }

}
