package fi.tuni.prog3.sisu;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.MalformedURLException;

public class Module {
    String name;
    String code;
    int targetCredits;
    String outcomes;
    //String curriculumPeriodIds;
    //String validityPeriod;

    public Module(String code){
        this.code = code;
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
            if(code.startsWith("otm")){
                GetJsonData getJson_Module = new GetJsonData(2, code);
                sb = getJson_Module.getJsonDataFromURL();
            }
            else{
                GetJsonData getJson_Module = new GetJsonData(3, code);
                sb = getJson_Module.getJsonDataFromURL();
            }
            JsonObject obj = JsonParser.parseString(sb.toString()).getAsJsonObject();
            if(obj.getAsJsonObject("name").getAsJsonPrimitive("fi") == null){
                this.name = obj.getAsJsonObject("name").getAsJsonPrimitive("en").getAsString();
            }
            else{
                this.name = obj.getAsJsonObject("name").getAsJsonPrimitive("fi").getAsString();
            }
            
            //Rulet läpi
            
        }
        catch (MalformedURLException e){
        }
        catch (IOException e2){
        }
    }
}


