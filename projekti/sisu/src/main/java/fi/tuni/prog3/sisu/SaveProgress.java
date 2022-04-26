package fi.tuni.prog3.sisu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 * Is used to save and load Student-objects.
 * 
 * <@author Joni Koskinen
 * @author Julius Juutilainen
 */
public class SaveProgress {

    public SaveProgress() {
    };

    /**
     * Saves a student object to JSON using GSON.
     * 
     * @param student Student object to be saved.
     * @throws IOException if the file coulnd't be saved.
     */
    public static void saveStudent(Student student) throws IOException {
        // ! For this to work, something unholy had to be done in module-info.java

        Gson gson = new Gson();
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showSaveDialog(null);

        FileWriter writer = new FileWriter(file);
        gson.toJson(student, writer);
        writer.close();

    }

    /**
     * Loads a student object from JSON usin GSON.
     * 
     * @return Student object loaded from file.
     * @throws FileNotFoundException if file user gives is not found.
     */
    public static Student loadStudent() throws FileNotFoundException {

        Gson gson = new Gson();
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("JSON Files", "*.json"));

        //Note: only files saved by the program should be opened
        File file = fileChooser.showOpenDialog(null);      
        
        //TODO: check if json format is correct for class Student.
        Student student = gson.fromJson(new FileReader(file), Student.class);
        return student;
 
    }
}
