package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.net.MalformedURLException;
import java.io.IOException;
import com.google.gson.*;
import java.util.HashMap;
import java.util.Iterator;



public class Degree {
    private String id;
    private String code;
    private String lang;
    private String groupId; //Module löytyy tällä?
    private String name;
    private int credits;
    HashMap<String, Module> moduleGroupIds;

    private StringBuilder sb; //Rakentimeen? ei kai
    
    
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
    
    public HashMap getModules(){
        decodeJson();
        return moduleGroupIds;
    }
    
    //TODO: Fiksumpi toteutus? Tää on aika paska.
    //TODO: Miten tämän saa suoritettua kun luokka initialisoidaan?

    public void decodeJson() {
        try{ 
            GetJsonData getJson_Module = new GetJsonData(2, id);
            StringBuilder sb = getJson_Module.getJsonDataFromURL();
            
            
            if(!sb.toString().equals("")){
                
                JsonObject obj = JsonParser.parseString(sb.toString()).getAsJsonObject();
                //Peruskamat otetaan talteen
                String id = obj.getAsJsonPrimitive("id").getAsString();
                String moduleName = "";
                if(obj.getAsJsonObject("name").getAsJsonPrimitive("fi") == null){
                    moduleName = obj.getAsJsonObject("name").getAsJsonPrimitive("en").getAsString();
                }
                else{
                    moduleName = obj.getAsJsonObject("name").getAsJsonPrimitive("fi").getAsString();
                }
                int targetCredits = obj.getAsJsonObject("targetCredits").getAsJsonPrimitive("min").getAsInt();
                
                
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
        }
        catch (MalformedURLException e){
        }
        catch (IOException e2){
        }

        
    }

    private void compositeRule(JsonArray arr) {
        Iterator<JsonElement> it = arr.iterator();
        while(it.hasNext()){
            JsonObject jObject = it.next().getAsJsonObject();
            String type = jObject.get("type").getAsString();
            moduleGroupIds = new HashMap<>();
            switch (type) {
                case "CompositeRule":
                    compositeRule(jObject.getAsJsonArray("rules"));
                    break;
                case "ModuleRule":                    
                    String moduleGroupId = jObject.get("moduleGroupId").getAsString();
                    Module m = new Module(moduleGroupId); 
                    moduleGroupIds.put(moduleGroupId, m);
                    break;
                default:
                    break;
            }
        }
    }
}
    

