package fi.tuni.prog3.sisu;

public class Degree {
    private String id;
    private String code;
    private String lang;
    private String name;
    private int credits;
    
    
    public Degree(String id, String code, String lang, String name){
        this.id = id;
        this.code = code;
        this.lang = lang;
        this.name = name;
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

    public String getName(){
        return name;
    }

    public int getCredits(){
        return credits;
    }
    
}
