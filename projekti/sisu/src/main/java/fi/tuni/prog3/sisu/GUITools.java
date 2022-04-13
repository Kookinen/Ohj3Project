package fi.tuni.prog3.sisu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.util.Duration;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUITools {
    
    public GUITools(){

    }

    public static Node getImageAsNode(String filename) throws FileNotFoundException{

        InputStream stream = new FileInputStream(filename);
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        
        return imageView;
    }

    public static Image getImage(String filename) throws FileNotFoundException{
        InputStream stream = new FileInputStream(filename);
        Image image = new Image(stream);

        return image;
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

}
