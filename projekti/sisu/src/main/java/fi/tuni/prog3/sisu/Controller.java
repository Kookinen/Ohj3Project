package fi.tuni.prog3.sisu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    private static String selectedElement;
    //private static HashMap<String, Boolean> coursesDone;
    
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
    private ComboBox searchBar = new ComboBox<>();
    @FXML
    private VBox completedCourses = new VBox();
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
        GUITools.setUpDegreeBox(searchBar, degrees);
        //selectableCourseList = GUITools.initializeCheckList(degrees);

        mainView.setRoot(rootItem);

        studentNumber.setText(student.getNumber());
        studentName.setText(student.getName());
        
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
            sb.setLength(sb.length()-1);
            String name = sb.toString();
            setSelectedElement(name);
            
            Course c = searchCourse(name);
            
            if(c!=null){
                if(c.getContent()!=null){
                    courseInfo.getEngine().loadContent("");
                    courseInfo.getEngine().loadContent(c.getContent());              
                }
                else if(c.getAdditional() != null){
                    courseInfo.getEngine().loadContent("");
                    courseInfo.getEngine().loadContent(c.getAdditional());
                }
                
                
            }
            Module m = searchModule(splitString[0]);
            if(m!=null){
                courseInfo.getEngine().loadContent("");
                courseInfo.getEngine().loadContent(m.getOutcomes());
                
            }
            if(name.equals(degree.getName())){
                courseInfo.getEngine().loadContent("");
                //courseInfo.getEngine().loadContent(degree.getOutcomes());
                
            }

            //Checkboxin nollaus jos dataa ei tulekkaan
            courseCheckBox.setSelected(false);

            if(allCourses.containsKey(Controller.selectedElement)){
                courseCheckBox.setVisible(true);
                if(student.getCoursesDone().get(Controller.selectedElement) == null || student.getCoursesDone() == null){
                    student.addCoursesDone(Controller.selectedElement, courseCheckBox.isSelected());
                }
                courseCheckBox.setSelected(student.getCoursesDone().get(Controller.selectedElement));
            } else {
                courseCheckBox.setVisible(false);
            }
            
                
            

            //Checkboxin status haetaan
            //courseCheckBox.setSelected(student.getCoursesDone().get(Controller.selectedElement));
        }
    }

    @FXML
    public void checkBoxOnClick(){

        student.addCoursesDone(Controller.selectedElement,courseCheckBox.isSelected());
        courseCheckBox.setSelected(student.getCoursesDone().get(Controller.selectedElement));
        refreshStudiesCompleted();

    }

    @FXML 
    void refreshStudiesCompleted(){
        completedCourses.getChildren().clear();

        for(String key : student.getCoursesDone().keySet()){
            if(student.getCoursesDone().get(key)){
                String credits = String.valueOf(allCourses.get(key).maxCredits);
                StringBuilder sb = new StringBuilder();
                sb.append(key+" "+credits+"op");

                String course = sb.toString();
                
                Text completedCourse = new Text(course);
                completedCourses.getChildren().add(completedCourse);
            }
        }
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

        refreshStudiesCompleted();
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

    public static void setSelectedElement(String element){
        Controller.selectedElement = element;
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
