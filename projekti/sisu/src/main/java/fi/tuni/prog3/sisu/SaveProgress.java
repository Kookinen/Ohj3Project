package fi.tuni.prog3.sisu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SaveProgress {

    public SaveProgress(){}

    //Not works :( wont write wont read wont do jack
    public static void saveStudent() throws IOException{

        File jsonFile = new File("src/java/resources/student.json");
        
        jsonFile.createNewFile();
        //Student studentToSave = new Student(student.getName(), student.getNumber());
        //Gson gson = new Gson();
        FileWriter writer = new FileWriter(jsonFile);
        //gson.toJson(studentToSave, writer);
        writer.write("str");
        writer.close();

    }

    public static Student loadStudent() throws FileNotFoundException{

        Gson gson = new Gson();
        
        Student student = gson.fromJson(new FileReader("student.json"),Student.class);
        return student;    

    }
}
