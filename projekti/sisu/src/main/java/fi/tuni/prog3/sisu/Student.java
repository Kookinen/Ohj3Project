package fi.tuni.prog3.sisu;

import java.util.HashMap;


public class Student {
    private String name;
    private String number;
    private static String degree;
    private int credits = 0;

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
        degree = newDegree;
        clearCoursesDone();
        credits = 0;
    }

    public int getCredits(){
        return credits;
    }

    public void addCredits(int earnedCredits){
        credits = credits+earnedCredits;
    }

    public void subtractCredits(int lostCredits){
        credits = credits-lostCredits;
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

    public void clearCoursesDone(){
        this.coursesDone.clear();
    } 
}
