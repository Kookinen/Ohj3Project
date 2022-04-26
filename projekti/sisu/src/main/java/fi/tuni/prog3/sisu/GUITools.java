package fi.tuni.prog3.sisu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * A set of GUI tools to add functionality to the GUI.
 * Contains unused functions, useful for futher developement.
 * 
 * @author Joni Koskinen
 * @author Julius Juutilainen
 */
public class GUITools {
    
    private static Student student;
    
    public GUITools() {
    }

    /**
     * Gets image from filepath.
     * 
     * @param filename path to file.
     * @return Image.
     * @throws FileNotFoundException if file is not found.
     */
    public static Image getImage(String filename) throws FileNotFoundException {

        InputStream stream = new FileInputStream(filename);
        Image image = new Image(stream);

        return image;
    }

    /**
     * Gets image from filepath as Node
     * 
     * @param filename path to file.
     * @return Node containing image.
     * @throws FileNotFoundException if file is not found.
     */
    public static Node getImageAsNode(String filename) throws FileNotFoundException {

        Image image = getImage(filename);
        ImageView imageNode = new ImageView();
        imageNode.setImage(image);

        return imageNode;
    }

    /**
     * Gets new motivational image from https://inspirobot.me/.
     * Inspirobot uses AI to generate motivational images.
     * 
     * @return URL of the new image.
     */
    public static String getMotivationalImageUrl() {
        String imageURL = new String();
        try {
            URLConnection connection = new URL("https://inspirobot.me/api?generate=true").openConnection();
            InputStream input = connection.getInputStream();
            System.out.println(connection.getContentType());

            imageURL = new BufferedReader(new InputStreamReader(input)).readLine();
        } catch (IOException e) {
            System.out.println("Not found!");
        }
        return imageURL;
    }

    /**
     * Initialized the main GUI Degree TreeView.
     * 
     * @param deg chosen Degree to be used in the initialization.
     * @return TreeItem<String> visual representation of Degree contents shown in
     *         UI.
     */
    public static TreeItem<String> initializeTree(Degree deg) {
        TreeItem<String> rootItem = new TreeItem<>(deg.getName());
        Controller.clearMaps();
        printTree(deg.getModules(), rootItem);
        return rootItem;
    }

    /**
     * TODO: Dokumentoi
     * 
     * @param modules
     * @param root
     */
    private static void printTree(HashMap<String, DegreeModule> modules, TreeItem<String> root) {
        TreeItem<String> moduleItem;
        TreeItem<String> courseItem;

        // käydään kaikki modulet läpi
        for (DegreeModule m : modules.values()) {
            if (m.getType().equals("GroupingModule")) {
                moduleItem = new TreeItem<>(m.getName());
            } else {
                moduleItem = new TreeItem<>(m.getName() + " 0op/" + m.getTargetCredits() + "op");
            }

            Controller.addModules(m);
            root.getChildren().add(moduleItem);
            HashMap<String, Course> cors = m.getCourses();
            // käydään modulen alaiset kurssit ( jos on )
            for (Course c : cors.values()){
                
                Controller.addCourses(c);
                courseItem = new TreeItem<>(c.getName() + " " + c.getTargetCredits() + "op");
                // lisätään kurssi modulen alle
                moduleItem.getChildren().add(courseItem);
                if(student.getCoursesDone().containsKey(c.getName()) && student.getCoursesDone().get(c.getName())){
                    Controller.addCreditsToTree(courseItem, c.getTargetCredits(), true);
                }
            }
            HashMap<String, DegreeModule> mods = m.getModules();
            if (!mods.isEmpty()) {
                // modulesta uusi root kun kutsutaan uudestaan
                printTree(mods, moduleItem);
            }
        }
    }

    /**
     * TODO: Dokumentoi
     * 
     * @param cb
     * @param degrees
     */
    public static void setUpDegreeBox(ComboBox<String> cb, HashMap<String, Degree> degrees) {
        cb.setEditable(true);
        cb.setPromptText("Hae tutkinnon nimellä...");
        cb.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                String s = cb.getEditor().getText();
                cb.getItems().clear();

                compare(s);

            }

            private void compare(String s) {
                ArrayList<String> names = new ArrayList<>();
                for (Degree d : degrees.values()) {
                    if (d.getName().length() >= s.length()
                            && d.getName().substring(0, s.length()).compareToIgnoreCase(s) == 0) {
                        names.add(d.getName());
                    }
                }
                cb.getItems().addAll(names);
            }
        });
    }
    public static String[] splitString(String string){
        String[] splitValue = string.split(" ");
        int length = splitValue.length;
        String[] name = Arrays.copyOf(splitValue, length - 1);
        return name;
    }
    public static StringBuilder combineString(String[] nameArray){
        StringBuilder sb = new StringBuilder();
        for (String s : nameArray) {
            sb.append(s).append(" ");
        }
        sb.setLength(sb.length() - 1);
        return sb;
    }
    public static void setStudent(Student student) {
        GUITools.student = student;
    }
}
