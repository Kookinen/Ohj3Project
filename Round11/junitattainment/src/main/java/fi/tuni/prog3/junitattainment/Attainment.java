/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.junitattainment;

/**
 *
 * @author Joni
 */
public class Attainment implements Comparable<Attainment>{
    private String code;
    private String number;
    private int courseGrade;
    
    public Attainment(String courseCode, String studentNumber, int grade){
        if(courseCode == null || studentNumber == null || grade < 1 || grade > 5){
            throw new IllegalArgumentException("Invalid course code, student number or grade!");
        }
        code = courseCode;
        number = studentNumber;
        courseGrade = grade;
        
    }
    
    public String getCourseCode(){
        return code;
    }
    public String getStudentNumber(){
        return number;
    }
    public int getGrade(){
        return courseGrade;
    }
    
    public String toString(){
        String str = String.format("%s %s %d", code, number, courseGrade);
        return str;
    }
    
    @Override
    public int compareTo(Attainment o) {      
        if(number.compareTo(o.getStudentNumber())!= 0){
            return number.compareTo(o.getStudentNumber());
        }
        else{
            return code.compareTo(o.getCourseCode());
        } 
    }
}