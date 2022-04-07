package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.net.MalformedURLException;
import java.io.IOException;
/*import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;*/
import com.google.gson.*;
import java.util.Iterator;



public class Degree {
    private String id;
    private String code;
    private String lang;
    private String groupId; //Module löytyy tällä?
    private String name;
    private int credits;
    ArrayList <String> moduleGroupIds;

    private StringBuilder sb; //Rakentimeen? ei kai
    
    
    public Degree(String id, String code, String lang, String groupId, String name, int credits){
        this.id = id;
        this.code = code;
        this.lang = lang;
        this.groupId = groupId;
        this.name = name;
        this.credits = credits;

        decodeJson();
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

    
    //TODO: Fiksumpi toteutus? Tää on aika paska.
    //TODO: Miten tämän saa suoritettua kun luokka initialisoidaan?

    public void decodeJson() {
        try{ 
            GetJsonData getJson_Module = new GetJsonData(2, groupId);
            StringBuilder sb = getJson_Module.getJsonDataFromURL();
            
            JsonObject obj = JsonParser.parseString(sb.toString()).getAsJsonObject();
            JsonArray arr = obj.getAsJsonArray("searchResults");
            Iterator<JsonElement> it = arr.iterator();

            while(it.hasNext()){
                JsonObject jObject = it.next().getAsJsonObject();

                
                //TODO: Tietorakenne moduuli-olioille Degreen sisllä. Tiedot moduleGroupIdList:istä ja GetJsonDatalla.
                //!! atm Heittää ERRORIn eli dataa ei läpikäydä oikein. 

                //Haetaan moduleGroupId:t arrayListiin. Näillä voidaan hakea moduulit osaksi Degreetä.
                JsonArray moduleGroupIdList = jObject.get("rule").getAsJsonObject().get("rule").getAsJsonObject().get("rules").getAsJsonObject().get("rules").getAsJsonArray();
                Iterator<JsonElement> it2 = moduleGroupIdList.iterator();
                while(it2.hasNext()){
                    JsonObject jObject2 = it2.next().getAsJsonObject(); 

                    System.out.print(jObject2.get("moduleGroupId").getAsString()); //!! Testi saadulle datalle

                    moduleGroupIdList.add(jObject2.get("moduleGroupId").getAsString());
                }


            
            }        
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e2){
         e2.printStackTrace();
        }

        
    }
}
    

