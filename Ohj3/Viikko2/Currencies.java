/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
public class Currencies {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException{
        
        BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String,Double> rates = new TreeMap<>();
        while(true){
            System.out.print("Enter command: ");
            final String command = user.readLine();
            String[] splitStr = command.split("\\s+");
            ArrayList<String> commands = new ArrayList<>(Arrays.asList(splitStr));

            

            if(commands.size()== 3){
               String c1 = commands.get(1);
               String c2 = commands.get(2);
               if(commands.get(0).equals("rate")){
                   Double amount = Double.parseDouble(c2);
                   rates.put(c1.toLowerCase(),amount);
                   System.out.format("Stored the rate 1 EUR = %.3f %s %n", amount, c1.toUpperCase());
                   

               } 
               else if(commands.get(0).equals("convert")){
                   Double amount = Double.parseDouble(c1);
                   String c2_lower = c2.toLowerCase();
                   if(rates.containsKey(c2_lower)){
                       Double euro = amount/rates.get(c2_lower);
                       System.out.format("%.3f %s = %.3f EUR%n", amount, c2.toUpperCase(), euro);
                   }
                   else{
                       System.out.format("No rate for %s has been stored!%n", c2.toUpperCase());
                   }
                }
                else{
                    System.err.println("Unknown or illegal command!");
                }
            }
            else if(commands.size()== 1){
                if(commands.get(0).equals("rates")){
                    System.out.println("Stored euro rates:");
                    for(Map.Entry<String, Double> entry: rates.entrySet()){
                        System.out.format("%4s %.3f %n", entry.getKey().toUpperCase(), entry.getValue());
                    }
                }
                else if(commands.get(0).equals("quit")){
                   System.out.println("Quit-command received, exiting...");
                   break;
                }
                else{
                    System.err.println("Unknown or illegal command!");
                }
            }
            else{
                System.err.println("Unknown or illegal command!");
            }
        }
    }
}
