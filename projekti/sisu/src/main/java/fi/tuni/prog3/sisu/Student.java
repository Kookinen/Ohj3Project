package fi.tuni.prog3.sisu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;


public class Student {
    private String name;
    private String number;
    private String degree;
    private HashMap<String, Boolean> coursesDone = new HashMap<>();
    
    
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

    public String getDegree(){
        return degree;
    }

    public void setDegree(String newDegree){
        this.degree = newDegree;
    }

    @Override
    public String toString(){
        return String.format("%s%n %s%n",this.name,this.number);
    }

    public HashMap<String, Boolean> getCoursesDone(){
        return coursesDone;
    }

    //Tarvitaanko delete vai voiko opiskelija vain lisätä kursseja
    public void addCoursesDone(String course, Boolean status){
        this.coursesDone.put(course, status);
    }
    //Not works
    
    
    
    
}
