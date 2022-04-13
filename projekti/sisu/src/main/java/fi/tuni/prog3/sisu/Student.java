package fi.tuni.prog3.sisu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Student {
    String name;
    String number;
    
    
    public Student(String name, String number){
        this.name = name;
        this.number = number;
        
        
    }
    public String getName(){
        return name;
    }
    public String getNumber(){
        return number;
    }
    public void saveStudent(){
        Student student = new Student(name, number);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = Files.newBufferedWriter(Paths.get("student.json"))) {
            gson.toJson(student, writer);
        }
        catch(IOException e){
        }
    }
    
    
    
}
