package fi.tuni.prog3.sisu;

public class Course {
    String id;
    //String groupId;
    String name;
    String code;
    int credits;
    //int gradeScaleId;
    String outcomes;

    public Course(String code){
        this.code = code;
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
