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

public class Parameters {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ArrayList<String> names = new ArrayList<>();
        int longest=0;
        for(String s:args){
            if(s.length()>longest){
                longest = s.length();
            }
            names.add(s);
        }
        Collections.sort(names, (a,b) -> a.compareToIgnoreCase(b));
        int size = names.size();
        String x = String.valueOf(size);
        int width = x.length();
        for(int i=0;i<longest+width+7;i++){
            if(i==longest+width+6){
                System.out.print("#"+'\n');
            }
            else{
                System.out.print("#");
            }
            
        }
        int maxLength = longest+width+7;
        for(int i=0;i<size;i++){
            int mones = i;
            System.out.format("# %"+width+"d | %-"+longest+"s #%n" , mones+1, names.get(mones));
            if(i==size-1){
                printChar('#',maxLength);
                printChar('\n',1);
            }
            else{
                printChar('#',1);
                printChar('-',width+2);
                printChar('+',1);
                printChar('-',longest+2);
                printChar('#',1);
                printChar('\n',1);
            }
            
        }
        
    }
    static void printChar(char c, int width) {
    for(int i = 0; i < width; i++) 
    { System.out.print(c); }
    }
}
