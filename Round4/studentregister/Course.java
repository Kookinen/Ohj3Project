/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
public class Course {
    private String courseCode;
    private String courseName;
    private int courseCredits;
    
    public Course(String code, String name, int credits){
        courseCode = code;
        courseName = name;
        courseCredits = credits;
    }
    
    public String getCode(){
        return courseCode;
    }
    public String getName(){
        return courseName;
    }
    public int getCredits(){
        return courseCredits;
    }
}
