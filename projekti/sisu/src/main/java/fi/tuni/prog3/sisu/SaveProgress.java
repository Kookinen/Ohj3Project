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

        // Multiple file ends so a user can create new JSON in the file explorer.
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("JSON Files", "*.json"),
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showOpenDialog(null);

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
    // TODO: tiedoston kelvollisuus tarkasteluun
    public static Student loadStudent() throws FileNotFoundException {

        Gson gson = new Gson();
        FileChooser fileChooser = new FileChooser();

        // Jäsenmuuttujiksi?
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showOpenDialog(null);
        Student student = gson.fromJson(new FileReader(file), Student.class);
        return student;

    }
}
