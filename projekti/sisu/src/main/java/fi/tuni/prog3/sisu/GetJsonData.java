package fi.tuni.prog3.sisu;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;


public class GetJsonData {

    private int urlId;
    private String moduleGroupId;

    public GetJsonData(int urlId, String moduleGroupId) throws MalformedURLException, IOException{
        this.urlId = urlId;
        this.moduleGroupId = moduleGroupId;
    }

    
    public StringBuilder getJsonDataFromURL() throws MalformedURLException, IOException{
        URL urlOne = new URL("https://sis-tuni.funidata.fi/kori/api/module-search?"
                + "curriculumPeriodId=uta-lvv-2021&universityId=tuni-university"
                + "-root-id&moduleType=DegreeProgramme&limit=1000");
        URL urlTwo = new URL(String.format("https://sis-tuni.funidata.fi/kori/api/modules/"+moduleGroupId)); 
        URL urlThree = new URL(String.format("https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="+moduleGroupId+"&universityId=tuni-university-root-id"));

        HttpURLConnection c = null;
        if (urlId == 1){
            c = (HttpURLConnection) urlOne.openConnection();
        }
        if(urlId == 2){
            c = (HttpURLConnection) urlTwo.openConnection();
        }
        if (urlId == 3){
            c = (HttpURLConnection) urlThree.openConnection();
        }

        String line;
        StringBuilder sb = new StringBuilder();
        c.setRequestMethod("GET");
        c.setConnectTimeout(5000);
        c.setReadTimeout(5000);
        if(c.getResponseCode()<300){
            BufferedReader read = new BufferedReader(new InputStreamReader(c.getInputStream()));
            while((line = read.readLine()) != null){
                sb.append(line);
            }
            read.close();
        }
        else{
            System.out.println("Not works :(");
        }
        c.disconnect();
        
        return sb;
    }
}

