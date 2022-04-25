package fi.tuni.prog3.sisu;

import java.net.MalformedURLException;
import java.io.IOException;
import com.google.gson.*;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Degree represents a degree in the Sisu API. It is used to store and fetch data.
 * @author Joni Koskinen
 * @author Julius Juutilainen
 */
public class Degree {
    private String id;
    private String code;
    private String lang;
    private String groupId; //Module löytyy tällä?
    private String name;
    private int credits;
    private String outcomes;
    HashMap<String, Module> modules;

    //private StringBuilder sb; //Rakentimeen? ei kai
    
    /**
     * 
     * @param id
     * @param code
     * @param lang
     * @param groupId
     * @param name
     * @param credits
     */
    public Degree(String id, String code, String lang, String groupId, String name, int credits){
        this.id = id;
        this.code = code;
        this.lang = lang;
        this.groupId = groupId;
        this.name = name;
        this.credits = credits;
        
        
    }

    
    /** 
     * @return String
     */
    public String getId(){
        return id;
    }

    
    /** 
     * @return String
     */
    public String getCode(){
        return code;
    }

    
    /** 
     * @return String
     */
    public String getLang(){
        return lang;
    }

    
    /** 
     * @return String
     */
    public String getGroupId(){
        return groupId;
    }

    
    /** 
     * @return String
     */
    public String getName(){
        return name;
    }

    
    /** 
     * @return int
     */
    public int getCredits(){
        return credits;
    }
    
    
    /** 
     * @return String
     */
    public String getOutcomes(){
        return outcomes;
    }
    
    
    /**
     * @return HashMap<String, Module>
     */
    public HashMap<String, Module> getModules(){
        decodeJson();
        return modules;
    }
    
    /**
     * 
     */
    public void decodeJson() {
        try{ 
            GetJsonData getJson_Module = new GetJsonData(2, id);
            StringBuilder sb = getJson_Module.getJsonDataFromURL();  
            JsonObject obj = JsonParser.parseString(sb.toString()).getAsJsonObject();
            
            //Peruskamat otetaan talteen
            if(!obj.get("learningOutcomes").isJsonNull()){
                if(obj.getAsJsonObject("learningOutcomes").get("fi") == null){
                    this.outcomes = obj.getAsJsonObject("learningOutcomes").getAsJsonPrimitive("en").getAsString();
                }
                else{
                    this.outcomes = obj.getAsJsonObject("learningOutcomes").getAsJsonPrimitive("fi").getAsString();
                }
            }
                
                
            // Rulet käydää läpi
            JsonArray arr = null;
            String rule = obj.getAsJsonObject("rule").get("type").getAsString();
            if(rule.equals("CreditsRule")){
                arr = obj.getAsJsonObject("rule").getAsJsonObject("rule").getAsJsonArray("rules");
            }
            else if(rule.equals("CompositeRule")){
                arr = obj.getAsJsonObject("rule").getAsJsonArray("rules");
            }
            compositeRule(arr);
        }
        catch (MalformedURLException e){
        }
        catch (IOException e2){
        }

        
    }

    
    /** 
     * @param arr
     */
    private void compositeRule(JsonArray arr) {
        Iterator<JsonElement> it = arr.iterator();
        modules = new HashMap<>();
        while(it.hasNext()){
            JsonObject jObject = it.next().getAsJsonObject();
            String type = jObject.get("type").getAsString();
            switch (type) {
                case "CompositeRule":
                    compositeRule(jObject.getAsJsonArray("rules"));
                    break;
                case "ModuleRule":                    
                    String moduleGroupId = jObject.get("moduleGroupId").getAsString();
                    Module m = new Module(moduleGroupId); 
                    modules.put(m.getName(), m);
                    break;
                default:
                    break;
            }
        }
    }
}
    

