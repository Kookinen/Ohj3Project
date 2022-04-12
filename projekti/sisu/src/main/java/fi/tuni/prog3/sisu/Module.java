package fi.tuni.prog3.sisu;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
//Listoja joihin moduuleita ja kursseja
public class Module {
    String name;
    String code;
    int targetCredits;
    String outcomes;
    //String curriculumPeriodIds;
    //String validityPeriod;
    HashMap<String, Module> modules;
    HashMap<String, Course> courses;

    public Module(String code){
        this.code = code;
        modules = new HashMap<>();
        courses = new HashMap<>();
        decodeJson();
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

    private void decodeJson() {
        try{
            StringBuilder sb = new StringBuilder();
            JsonObject obj;
            if(code.startsWith("otm")){
                GetJsonData getJson_Module = new GetJsonData(2, code);
                sb = getJson_Module.getJsonDataFromURL();
                obj = JsonParser.parseString(sb.toString()).getAsJsonObject();
            }
            else{
                GetJsonData getJson_Module = new GetJsonData(3, code);
                sb = getJson_Module.getJsonDataFromURL();
                obj =  JsonParser.parseString(sb.toString()).getAsJsonArray().get(0).getAsJsonObject();
            }
            if(obj.getAsJsonObject("name").getAsJsonPrimitive("fi") == null){
                this.name = obj.getAsJsonObject("name").getAsJsonPrimitive("en").getAsString();
            }
            else{
                this.name = obj.getAsJsonObject("name").getAsJsonPrimitive("fi").getAsString();
            }
            
            //Rulet läpi
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
    
    private void compositeRule(JsonArray arr) {
        Iterator<JsonElement> it = arr.iterator();
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
                    modules.put(moduleGroupId, m);
                    break;
                case "CourseUnitRule":
                    String courseId = jObject.get("courseUnitGroupId").getAsString();
                    Course c = new Course(courseId);
                    courses.put(courseId, c);
                    break;
                default:
                    break;
            }
        }
    }
}


