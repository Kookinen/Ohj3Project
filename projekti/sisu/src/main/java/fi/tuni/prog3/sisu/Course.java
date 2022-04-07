package fi.tuni.prog3.sisu;

public class Course {
    String id;
    //String groupId;
    String name;
    String code;
    int credits;
    //int gradeScaleId;
    String outcomes;

    public Course(String id, String name, String code, int credits, String outcomes){
        this.id = id;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.outcomes = outcomes;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }

    public int getTargetCredits(){
        return credits;
    }

    public String getOutcomes(){
        return outcomes;
    }
}
