package fi.tuni.prog3.sisu;

public class Degree {
    private String id;
    private String code;
    private String lang;
    private String groupId;
    private String name;
    private int credits;
    
    
    public Degree(String id, String code, String lang, String groupId, String name, int credits){
        this.id = id;
        this.code = code;
        this.lang = lang;
        this.groupId = groupId;
        this.name = name;
        this.credits = credits;
    }

    public String getId(){
        return id;
    }

    public String getCode(){
        return code;
    }

    public String getLang(){
        return lang;
    }

    public String getGroupId(){
        return groupId;
    }

    public String getName(){
        return name;
    }

    public int getCredits(){
        return credits;
    }

    //TODO: Lista Module-olioista jotka kuuluvat Degreeseen
    
}
