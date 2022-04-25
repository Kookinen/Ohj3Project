package fi.tuni.prog3.sisu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/*import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;*/

public class SaveProgress {

    public SaveProgress(){}

    
    /** 
     * @param student
     * @throws IOException
     */
    //! Jotta tämä toimisi, piti tehdä jotakin epäpyhää module-info.java:ssa. Miksi se toimii ei ole tiedossa.
    //! Jos Studentia muokataan voi olla, että pitää lisätä asioita...
    public static void saveStudent(Student student) throws IOException{

        //Student studentToSave = new Student(student.getName(), student.getNumber());
        Gson gson = new Gson();

        FileChooser fileChooser = new FileChooser();

        //Jos käyttäjä haluaa luoda uuden tiedoston, joutuu hän nimeämään txt:n uudelleen.
        //Siksi tässä on useita tiedostopäätteitä.
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("JSON Files", "*.json"),
            new ExtensionFilter("Text Files", "*.txt"),
            new ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showOpenDialog(null);
        //Jesus christ help us
        //File file = new File("student.json");
        FileWriter writer = new FileWriter(file);
        gson.toJson(student, writer);
        writer.close();

    }

    
    /** 
     * @return Student
     * @throws FileNotFoundException
     */
    //TODO: tiedoston kelvollisuus tarkasteluun
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
