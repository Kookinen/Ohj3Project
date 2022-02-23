/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.sevenzipsearch;

/**
 *
 * @author Joni
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.commons.compress.archivers.sevenz.*;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;





public class zipMain {
    public static void main(String[] args) {
        
        String filename = args[0];
        String word = args[1];
        try(SevenZFile z = new SevenZFile(new File(filename))){
            SevenZArchiveEntry entry;
            while((entry = z.getNextEntry())!=null){
                if(entry.getName().substring(entry.getName().length()-4).equals(".txt")){
                    FileOutputStream out = new FileOutputStream(entry.getName());
                    byte[] content = new byte[(int)entry.getSize()];
                    z.read(content, 0, content.length);
                    out.write(content);
                    String s = new String(content, StandardCharsets.UTF_8);
                    
                    out.close();
                    readTextFile(entry.getName(), word);
                    
                }
                
            
            }
            
            
            
        }
        catch(IOException e){
               System.out.print("erroro"); 
        }
    }
    public static void readTextFile(String filename, String word) throws FileNotFoundException, IOException{
        System.out.println(filename);
        String line = null;
        int lineCount=1;
        
        var input = new BufferedReader(new FileReader(filename));
        
        while((line = input.readLine())!= null){
            
            boolean searching = true;
            String lowerLine = line.toLowerCase();
            if(lowerLine.contains(word.toLowerCase())){
                int startFrom = 0;
                String mod = "";
                while(searching){
                    int index = lowerLine.indexOf(word.toLowerCase(),startFrom);
                    if(index>=0){
                        mod = line.substring(startFrom,index)+line.substring(index,index+word.length()).toUpperCase()+line.substring(index+word.length());
                        startFrom = index+word.length();
                    }
                    else{
                        searching = false;
                    }
                }
                System.out.println(lineCount+": "+mod);
            }
            lineCount+=1;
        }
        System.out.println();
    }
}
