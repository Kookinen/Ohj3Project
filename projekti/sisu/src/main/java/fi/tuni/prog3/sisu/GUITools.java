package fi.tuni.prog3.sisu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Duration;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class GUITools {
    
    public GUITools(){}

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
    

    public static TreeItem<String> initializeTree(Degree deg){
        TreeItem<String> rootItem = new TreeItem<>(deg.getName());
        Controller.clearMaps();
        printTree(deg.getModules(), rootItem);
        return rootItem;
    }

    /*public static VBox initializeCheckList(HashMap<String, Degree> degrees){
        
        VBox checkBoxList = new VBox();
        Degree deg = degrees.get("Tietojenkäsittelytieteiden kandidaattiohjelma");
        HashMap<String, Module> modules = deg.getModules();

        for(Module m:modules.values()){
            CheckBox check = new CheckBox(m.getName());
            checkBoxList.getChildren().add(check);
            
            HashMap<String, Course> courses = m.getCourses();
            for(Course c:courses.values()){
                CheckBox checkOneTwo = new CheckBox(c.getName());
                checkBoxList.getChildren().add(checkOneTwo);
            }
        }

        return checkBoxList;
    }*/
    
    private static void printTree(HashMap<String, Module> modules, TreeItem root){
        TreeItem<String> moduleItem;
        TreeItem<String> courseItem;
        
        //käydään kaikki modulet läpi
        for(Module m:modules.values()){
            moduleItem = new TreeItem<>(m.getName()+ " " + m.getTargetCredits()+ "op");
            Controller.addModules(m);
            root.getChildren().add(moduleItem);
            HashMap<String, Course> cors = m.getCourses();
            //käydään modulen alaiset kurssit ( jos on )
            for(Course c:cors.values()){
                Controller.addCourses(c);
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
    
    public static void setUpDegreeBox(ComboBox cb, HashMap<String, Degree> degrees){
        cb.setEditable(true);
        cb.setPromptText("Hae tutkinnon nimellä...");
        cb.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent k){
                String s = cb.getEditor().getText();
                cb.getItems().clear();
                
                compare(s);
               
            }

            private void compare(String s) {
                
                ArrayList<String> names = new ArrayList<>();
                for(Degree d:degrees.values()){
                    if(d.getName().length() >= s.length() && d.getName().substring(0, s.length()).compareToIgnoreCase(s)==0){
                        names.add(d.getName());
                    }
                }
                
                cb.getItems().addAll(names);
            }
        });
    }

}
