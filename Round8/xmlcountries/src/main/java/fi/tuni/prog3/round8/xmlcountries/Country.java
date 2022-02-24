/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.round8.xmlcountries;

/**
 *
 * @author Joni
 */
public class Country implements Comparable<Country>{

    private final String name;
    private final Double area;
    private final Long population;
    private final Double gdp;
    public Country(String name, Double area, Long population, Double gdp){
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
    }

    @Override
    public int compareTo(Country o) {
        return name.compareTo(o.getName());
    }
    
    public String toString(){
        String s = String.format("%s\n  Area: %.1f km2\n  Population: %d\n  GDP:"
                + " %.1f (2015 USD)\n", name, area, population, gdp);
        return s;
    }
    
    public String getName(){
        return name;
    }
    public Double getArea(){
        return area;
    }
    public Long getPopulation(){
        return population;
    }
    public Double getGdp(){
        return gdp;
    }
    
}
