package fi.tuni.prog3.sisu;

public class Module {
    String name;
    String code;
    int targetCredits;
    String outcomes;
    //String curriculumPeriodIds;
    //String validityPeriod;

    public Module(String name, String code, int targetCredits, String outcomes){
        this.name = name;
        this.code = code;
        this.targetCredits = targetCredits;
        this.outcomes = outcomes;
    }

    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }

    public int getTargetCredits(){
        return targetCredits;
    }

    public String getOutcomes(){
        return outcomes;
    }

    //TODO: Lista Course-olioista jotka kuuluvat Moduleen
}

