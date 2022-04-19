package fi.tuni.prog3.sisu;

import java.io.BufferedWriter;
import java.io.File;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/*import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;*/

public class SaveProgress {

    public SaveProgress(){}

    //TODO: saveStudentin ja loadStudentin json-tiedosto src/ kansioon. Tiedostopolku vaan 404.
    //! Jotta tämä toimisi, piti tehdä jotakin epäpyhää module-info.java:ssa. Miksi se toimii ei ole tiedossa.

    public static void saveStudent(Student student) throws IOException{

        Student studentToSave = new Student(student.getName(), student.getNumber());
        Gson gson = new Gson();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("JSON Files", "*.json"),
            new ExtensionFilter("Text Files", "*.txt"),
            new ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showOpenDialog(null);
        //Jesus christ help us
        //File file = new File("student.json");
        FileWriter writer = new FileWriter(file);
        gson.toJson(studentToSave, writer);
        writer.close();

    }

    public static Student loadStudent() throws FileNotFoundException{

        Gson gson = new Gson();
        FileChooser fileChooser = new FileChooser();

        //Jäsenmuuttujiksi?
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("JSON Files", "*.json")
        );
        File file = fileChooser.showOpenDialog(null);
        Student student = gson.fromJson(new FileReader(file),Student.class);
        return student;    

    }
}
