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

    public static void saveStudent(Student student)throws IOException{

        Student studentToSave = new Student(student.getName(), student.getNumber());
        Gson gson = new Gson();
        Writer writer = new FileWriter("/student.json");
        gson.toJson(studentToSave, writer);
        writer.close();

    }

    public static Student loadStudent() throws FileNotFoundException{

        Gson gson = new Gson();
        
        Student student = gson.fromJson(new FileReader("student.json"),Student.class);
        return student;    

    }
}
