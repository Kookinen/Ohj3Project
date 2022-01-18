/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
import java.util.ArrayList;
import java.util.Collections;
public class Median {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        double x;
        ArrayList<Double> numbs = new ArrayList<>();
        for(String s:args){
            x = Double.parseDouble(s);
            numbs.add(x);
        }
        Collections.sort(numbs);
        double result;
        if(numbs.size()%2==0){
            result=(numbs.get(numbs.size()/2)+numbs.get(numbs.size()/2-1))/2;
        }
        else{
            result=numbs.get(numbs.size()/2);
        }
        
        System.out.println("Median: " + result);
    }
}
