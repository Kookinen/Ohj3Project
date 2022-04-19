package fi.tuni.prog3.sisu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class Student {
    private String name;
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

    @Override
    public String toString(){
        return String.format("%s%n %s%n",this.name,this.number);
    }
    //Not works
    
    
    
    
}
