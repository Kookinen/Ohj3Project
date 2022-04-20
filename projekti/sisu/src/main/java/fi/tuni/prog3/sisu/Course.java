package fi.tuni.prog3.sisu;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.MalformedURLException;

public class Course {
    String id;
    //String groupId;
    String name;
    String code;
    int minCredits;
    int maxCredits;
    //int gradeScaleId;
    String content;
    String additional;

    public Course(String id){
        this.id = id;
        getInfo();
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
        return minCredits;
    }

    public String getContent(){
        return content;
    }
    public String getAdditional(){
        return additional;
    }

    private void getInfo() {
        try{
           
            GetJsonData getJson_Module = new GetJsonData(4, id);
            StringBuilder sb = getJson_Module.getJsonDataFromURL();
            
            
            JsonObject obj =  JsonParser.parseString(sb.toString()).getAsJsonArray().get(0).getAsJsonObject();
            this.minCredits = obj.getAsJsonObject("credits").getAsJsonPrimitive("min").getAsInt();
            
            
            if(obj.getAsJsonObject("credits").get("max").isJsonNull()){
                this.maxCredits = minCredits;
            }
            else{
                this.maxCredits = obj.getAsJsonObject("credits").getAsJsonPrimitive("max").getAsInt();
            }
            
            if(obj.getAsJsonObject("name").getAsJsonPrimitive("fi") == null){
                this.name = obj.getAsJsonObject("name").getAsJsonPrimitive("en").getAsString();
            }
            
            else{
                this.name = obj.getAsJsonObject("name").getAsJsonPrimitive("fi").getAsString();
            }
            this.code = obj.getAsJsonPrimitive("code").getAsString();
            
            if(!obj.get("content").isJsonNull()){
                if(obj.getAsJsonObject("content").get("fi") == null){
                    this.content = obj.getAsJsonObject("content").getAsJsonPrimitive("en").getAsString();
                }
                else{
                    this.content = obj.getAsJsonObject("content").getAsJsonPrimitive("fi").getAsString();
                }
            }
            if(!obj.get("additional").isJsonNull()){
                if(obj.getAsJsonObject("additional").get("fi") == null){
                    this.additional = obj.getAsJsonObject("additional").getAsJsonPrimitive("en").getAsString();
                }
                else{
                    this.additional = obj.getAsJsonObject("additional").getAsJsonPrimitive("fi").getAsString();
                }
            }
            
        }
        catch (MalformedURLException e){
        }
        catch (IOException e2){
        }       
    }
}
