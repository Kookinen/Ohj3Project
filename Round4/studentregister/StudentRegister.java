/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Set;

public class StudentRegister {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private HashMap<String, ArrayList<Attainment>> attainments;
    public StudentRegister(){
        students = new ArrayList<>();
        courses = new ArrayList<>();
        attainments = new HashMap<>();
    }
    public ArrayList<Student> getStudents(){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Student> studentsOrder = new ArrayList<>();
        for(int i = 0; i<students.size();i++){
            names.add(students.get(i).getName());
        }
        Collections.sort(names);
        for(int i = 0; i<names.size();i++){
            for(int j = 0; j<students.size(); j++){
                if(names.get(i) == students.get(j).getName()){
                    studentsOrder.add(students.get(j));
                }
            }
        }
        return studentsOrder;
    }
    public ArrayList<Course> getCourses(){
        ArrayList<String> courseNames = new ArrayList<>();
        ArrayList<Course> coursesOrder = new ArrayList<>();
        for(int i = 0; i<courses.size();i++){
            courseNames.add(courses.get(i).getName());
        }
        Collections.sort(courseNames);
        for(int i = 0; i<courseNames.size();i++){
            for(int j = 0; j<courses.size(); j++){
                if(courseNames.get(i) == courses.get(j).getName()){
                    coursesOrder.add(courses.get(j));
                }
            }
        }
        return coursesOrder;
    }
    
    public void addStudent(Student student){
        students.add(student);
    } 
    public void addCourse(Course course){
        courses.add(course);
    }
    public void addAttainment(Attainment att){
        if(attainments.containsKey(att.getStudentNumber())){
            attainments.get(att.getStudentNumber()).add(att);
        }
        else{
            ArrayList<Attainment> attainment = new ArrayList<>();
            attainment.add(att);
            attainments.put(att.getStudentNumber(), attainment);
        }
        
    }
    public void printStudentAttainments(String studentNumber, String order){
        boolean found = false;
        int index =0;
        for(int i = 0; i<students.size(); i++){
            if(studentNumber == students.get(i).getStudentNumber()){
                found = true;
                index = i;
            }
        }
        if(!found){
            System.out.println("Unknown student number: "+studentNumber);
        }
        else{
            System.out.println(students.get(index).getName()+" ("+
                    students.get(index).getStudentNumber()+"):");
            if(order == "by name"){
                ArrayList<Course> courseOrd = new ArrayList<>();
                ArrayList<Attainment> attains = attainments.get(studentNumber);
                
                HashMap<String, Attainment> courseMap = new HashMap<>();
                
                for(int i = 0; i<attains.size(); i++){
                    for(int j = 0; j<courses.size();j++){
                        if(attains.get(i).getCourseCode().equals(courses.get(j).getCode())){
                            
                            courseMap.put(courses.get(j).getName(), attains.get(i));
                        }
                    }
                }
                Set<String> courseNamesSet = courseMap.keySet();
                ArrayList<String> courseNames = new ArrayList<>(courseNamesSet);
                Collections.sort(courseNames);
                for(int i = 0; i<courseNames.size();i++){
                    String nimi = courseNames.get(i);
                    System.out.println("  "+courseMap.get(nimi).getCourseCode()+
                            " "+nimi+": "+courseMap.get(nimi).getGrade());
                }
                
                
            }
            else if(order == "by code"){
                ArrayList<String> courseCodes = new ArrayList<>();
                ArrayList<Attainment> attains = attainments.get(studentNumber);
                HashMap<String, Attainment> courseMap = new HashMap<>();
                ArrayList<String> nameOrd = new ArrayList<>();
                
                for(int i = 0; i<attains.size();i++){
                    courseCodes.add(attains.get(i).getCourseCode());
                }
                Collections.sort(courseCodes);
                
                for(int i = 0; i<courseCodes.size(); i++){
                    for(int j = 0; j<courses.size();j++){
                        if(courseCodes.get(i).equals(courses.get(j).getCode())){
                            courseMap.put(courses.get(j).getName(), attains.get(i));
                            nameOrd.add(courses.get(j).getName());
                        }
                    }
                }
                for(int i=0;i<nameOrd.size();i++){
                    String nimi = nameOrd.get(i);
                    System.out.println("  "+courseMap.get(nimi).getCourseCode()+
                            " "+nimi+": "+courseMap.get(nimi).getGrade());
                }
                     
            }
            else{
                printStudentAttainments(studentNumber);
            }
        }
    }
    
    public void printStudentAttainments(String studentNumber){
        boolean found = false;
        int index =0;
        for(int i = 0; i<students.size(); i++){
            if(studentNumber == students.get(i).getStudentNumber()){
                found = true;
                index = i;
            }
        }
        if(!found){
            System.out.println("Unknown student number: "+studentNumber);
        }
        else{
            
            System.out.println(students.get(index).getName()+" ("+
                    students.get(index).getStudentNumber()+"):");
            ArrayList<Attainment> attains = attainments.get(studentNumber);
            ArrayList<String> courseCodes = new ArrayList<>();
            HashMap<String, Attainment> courseMap = new HashMap<>();
            ArrayList<String> nameOrd = new ArrayList<>();
            
            
            for(int i = 0; i<attains.size();i++){
                    courseCodes.add(attains.get(i).getCourseCode());
            }
            
            for(int i = 0; i<courseCodes.size(); i++){
                for(int j = 0; j<courses.size();j++){
                    
                    if(courseCodes.get(i).equals(courses.get(j).getCode())){
                        courseMap.put(courses.get(j).getName(), attains.get(i));
                        nameOrd.add(courses.get(j).getName());
                    }
                }
            }
            
            for(int i=0;i<nameOrd.size();i++){
                String nimi = nameOrd.get(i);
                System.out.println("  "+courseMap.get(nimi).getCourseCode()+
                        " "+nimi+": "+courseMap.get(nimi).getGrade());
            }
            
        }
    }
    
}
