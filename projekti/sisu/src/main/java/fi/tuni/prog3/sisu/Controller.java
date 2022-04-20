package fi.tuni.prog3.sisu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;

/*
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
*/

public class Controller implements Initializable{
    
    private static HashMap<String, Degree> degrees;
    private static HashMap<String, Course> allCourses;
    private static HashMap<String, Module> allModules;
    private static Student student;
    private static Degree degree;
<<<<<<< HEAD
    private static HashMap<String, Boolean> coursesDone;
||||||| 423029c
=======

    
>>>>>>> f3d0a1ca0370f7890a9649c9cb5421285bf436c0
    
    @FXML
    private TreeView<String> mainView = new TreeView<>();
    @FXML
    private WebView courseInfo = new WebView();
    @FXML
    private Text studentName = new Text();
    @FXML
    private Text studentNumber = new Text();
    @FXML
    private Button saveButton = new Button();
    @FXML
    private Button loadButton = new Button();
    @FXML
    private TextField searchBar = new TextField();
    @FXML
    private VBox selectableCourseList = new VBox();
    @FXML
    private CheckBox courseCheckBox = new CheckBox();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        //otettu vain yksittäinen degree.
        //TODO: degree-lista josta valitaan mieluinen tai hakusysteemi
        allCourses = new HashMap<>();
        allModules = new HashMap<>();
        TreeItem<String> rootItem = GUITools.initializeTree(degree);
        //selectableCourseList = GUITools.initializeCheckList(degrees);

        mainView.setRoot(rootItem);

        studentNumber.setText(student.getNumber());
        studentName.setText(student.getName());

        coursesDone = student.getCoursesDone();
        
        
    }

    @FXML
    public void selectItem(){
        TreeItem<String> item = mainView.getSelectionModel().getSelectedItem();
        if(item != null){
            String courseHeader = item.getValue();
            String[] splitString = courseHeader.split(" ");
            String[] courseName = Arrays.copyOf(splitString, splitString.length-1);
            StringBuilder sb = new StringBuilder();
            for(String s : courseName){
                sb.append(s).append(" ");
            }
            String name = sb.toString();
            
<<<<<<< HEAD
            
            

            Course c = searchCourse(splitString[0]);
||||||| 423029c
            
            Course c = searchCourse(splitString[0]);
=======
            Course c = searchCourse(name.substring(0, name.length()-1));
            if(c!=null){
                courseInfo.getEngine().loadContent("");
                courseInfo.getEngine().loadContent(c.getContent());
            }
            Module m = searchModule(splitString[0]);
            if(m!=null){
                courseInfo.getEngine().loadContent("");
                courseInfo.getEngine().loadContent(c.getContent());
            }
>>>>>>> f3d0a1ca0370f7890a9649c9cb5421285bf436c0

           
            /*
            courseInfo.getChildren().clear();
            courseInfo.getChildren().add(new Text(c.getContent()));
            */
        }
        


        /*EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
               System.out.println("Hello World");  
            } 
        }; 
        mainView.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);*/
    }


    @FXML
    public void save(){
        System.out.println("Saving...");
        try {
            SaveProgress.saveStudent(student);

        } catch (IOException ex) {
            System.out.println("Couldn't save progress!");
        }
    }

    //TODO: Käyttäjä valitsee tiedoston mitä ladata itse?
    @FXML
    public void load(){
        System.out.println("Loading...");

        //Refresh-metodi?
        try{
            Controller.student = SaveProgress.loadStudent();
            studentNumber.setText(student.getNumber());
            studentName.setText(student.getName());
        }
        catch(FileNotFoundException e2){
            System.out.println("File not found :(");
        }
    }

  
     //Adding event Filter 

    public static void setDegrees(HashMap degrees){
        Controller.degrees = degrees;
    }
    
    public static void setStudent(Student student){
        Controller.student = student;
    }
    
    public static void setDegree(Degree degree){
        Controller.degree = degree;
    }
    public static void addCourses(Course c){
        allCourses.put(c.getName(), c);
    }
    public static void addModules(Module m){
        allModules.put(m.getName(), m);
    }
    public static void clearMaps() {
        if(allCourses != null){
            allCourses.clear();
        }
        if(allModules != null){
            allModules.clear();
        }
        
    }
    
    public static Course searchCourse(String name){
        if(allCourses.containsKey(name)){
            return allCourses.get(name);
        }
        return null;
    }
    public static Module searchModule(String name){
        if(allModules.containsKey(name)){
            return allModules.get(name);
        }
        return null;
    }
    
}
