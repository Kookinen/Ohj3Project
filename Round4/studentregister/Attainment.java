/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
public class Attainment {
    private String code;
    private String number;
    private int courseGrade;
    
    public Attainment(String courseCode, String studentNumber, int grade){
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
}
