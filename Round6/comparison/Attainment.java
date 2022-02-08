/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
import java.util.Comparator;
public class Attainment implements Comparable<Attainment>{
    private String code;
    private String number;
    private int courseGrade;
    public static final Comparator<Attainment> CODE_STUDENT_CMP = new Comparator<>(){
        @Override
        public int compare(Attainment first, Attainment other){
            int cmp = first.code.compareTo(other.code);
        if(cmp == 0){
            cmp = first.number.compareTo(other.number);
        }
        return cmp;
        }
    };
    
    public static final Comparator<Attainment> CODE_GRADE_CMP = new Comparator<>(){
       @Override
        public int compare(Attainment first, Attainment other){
            int cmp = first.code.compareTo(other.code);
        if(cmp == 0){
            cmp = Integer.compare(other.courseGrade, first.courseGrade);
        }
        return cmp;
        }
    };
    
    
    
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
    @Override
    public String toString(){
        return String.format("%s %s %d%n", code, number, courseGrade);
    }
    
    @Override
    public int compareTo(Attainment other){
        int cmp = number.compareTo(other.number);
        if(cmp == 0){
            cmp = code.compareTo(other.code);
        }
        return cmp;
    }
    
}