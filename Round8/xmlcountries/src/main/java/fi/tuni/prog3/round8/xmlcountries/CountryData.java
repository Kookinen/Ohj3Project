/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.round8.xmlcountries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;


import java.util.HashMap;
import java.util.List;
import org.jdom2.output.Format;

/**
 *
 * @author Joni
 */
public class CountryData {
    private static HashMap<String, Double> areaMap = new HashMap<>();
    private static HashMap<String, Long> popMap = new HashMap<>();
    private static HashMap<String, Double> gdpMap  = new HashMap<>();
   
    
    public static List<Country> readFromXmls(String areaFile, String populationFile, String gdpFile){
        try{
            SAXBuilder sax = new SAXBuilder();
            Document doc = sax.build(new File(areaFile));
            Element root = doc.getRootElement();
            Element data = root.getChild("data");
            List<Element> list = data.getChildren("record");
            for(Element e:list){
                List<Element> fieldList = e.getChildren();
                String countryName = "";
                Double area = 0.0;
                for(Element field:fieldList){  
                    if(field.getAttributeValue("name").equals("Country or Area")){
                        countryName = field.getText();
                    }
                    else if(field.getAttributeValue("name").equals("Value")){
                        area = Double.parseDouble(field.getText());
                    }
                }
                areaMap.put(countryName, area);
            }
            SAXBuilder saxGdp = new SAXBuilder();
            Document docGdp = saxGdp.build(new File(gdpFile));
            Element rootGdp = docGdp.getRootElement();
            Element data2 = rootGdp.getChild("data");
            List<Element> listGdp = data2.getChildren("record");
            for(Element e:listGdp){
                List<Element> fieldList = e.getChildren();
                String countryName = "";
                Double gdp = 0.0;
                for(Element field:fieldList){
                    if(field.getAttributeValue("name").equals("Country or Area")){
                        countryName = field.getText();
                    }
                    else if(field.getAttributeValue("name").equals("Value")){
                        gdp = Double.parseDouble(field.getText());
                    }
                }
                gdpMap.put(countryName, gdp);
            }
            SAXBuilder saxPop = new SAXBuilder();
            Document docPop = saxPop.build(new File(populationFile));
            Element rootPop = docPop.getRootElement();
            Element data3 = rootPop.getChild("data");
            List<Element> listPop = data3.getChildren("record");
            for(Element e:listPop){
                List<Element> fieldList = e.getChildren();
                String countryName = "";
                Long pop = 0L;
                for(Element field:fieldList){
                    if(field.getAttributeValue("name").equals("Country or Area")){
                        countryName = field.getText();
                    }
                    else if(field.getAttributeValue("name").equals("Value")){
                        pop = Long.parseLong(field.getText());
                    }
                }
                popMap.put(countryName, pop);
            }
            
        }
        catch(IOException | JDOMException e){
            System.out.println(e);
        }
        ArrayList<String> countries  = new ArrayList<>(areaMap.keySet());
        List<Country> countryList = new ArrayList<>();
        for(String c:countries){
            Country count = new Country(c, areaMap.get(c), popMap.get(c), gdpMap.get(c));
            countryList.add(count);
        }
        return countryList;
    }
    
    
    
    public static void writeToXml(List<Country> countries, String countryFile){
        Element counts = new Element("countries"); 
        for(Country c:countries){
            Element name = new Element("name");
            name.addContent(c.getName());
            Element area = new Element("area");
            area.addContent(c.getArea().toString());
            Element population = new Element("population");
            population.addContent(c.getPopulation().toString());
            Element country = new Element("country");
            country.addContent(name);
            country.addContent(area);
            country.addContent(population);
            Element gdp = new Element("gdp");
            if(c.getGdp()!=null){
                gdp.addContent(c.getGdp().toString());
                country.addContent(gdp);
            }
            
            counts.addContent(country);
        }
        Document countryBuild = new Document(counts);
        XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
        try(FileOutputStream outstream = new FileOutputStream(countryFile)){
            output.output(countryBuild, outstream);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
