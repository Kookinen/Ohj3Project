package fi.tuni.prog3.sisu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class Student {
    String name;
    private String number;
    
    
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
    public void saveStudent()throws IOException{
        Student student = new Student(name, number);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = new FileWriter("student.json");
        gson.toJson(student, writer);
    }
    
    
    
}
