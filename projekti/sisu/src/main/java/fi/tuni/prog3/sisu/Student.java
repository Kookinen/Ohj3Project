package fi.tuni.prog3.sisu;

import java.util.HashMap;

public class Student {
    private String name;
    private String number;
    private String degree;
    private int credits;

    private HashMap<String, Boolean> coursesDone = new HashMap<>();

    public Student(String name, String number) {
        this.name = name;
        this.number = number;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return String
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return String
     */
    public String getDegree() {
        return degree;
    }

    /**
     * @param newDegree
     */
    public void setDegree(String newDegree) {
        degree = newDegree;
        clearCoursesDone();
        credits = 0;
    }

    /**
     * @return int
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param earnedCredits
     */
    public void addCredits(int earnedCredits) {
        credits = credits + earnedCredits;
    }

    /**
     * @param lostCredits
     */
    public void subtractCredits(int lostCredits) {
        credits = credits - lostCredits;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s%n %s%n", this.name, this.number);
    }

    /**
     * @return HashMap<String, Boolean>
     */
    public HashMap<String, Boolean> getCoursesDone() {
        return coursesDone;
    }

    /**
     * @param course
     * @param status
     */
    // Tarvitaanko delete vai voiko opiskelija vain lisätä kursseja
    public void addCoursesDone(String course, Boolean status) {
        this.coursesDone.put(course, status);

    }

    public void clearCoursesDone() {
        this.coursesDone.clear();
    }
}
