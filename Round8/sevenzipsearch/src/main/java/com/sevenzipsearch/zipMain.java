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
import java.nio.charset.StandardCharsets;





public class zipMain {
    public static void main(String[] args) {
        
        String filename = args[0];
        String word = args[1];
        try(SevenZFile z = new SevenZFile(new File(filename))){
            SevenZArchiveEntry entry;
            while((entry = z.getNextEntry())!=null){
                if(entry.getName().substring(entry.getName().length()-4).equals(".txt")){
                    System.out.println(entry.getName());
                    byte[] content = new byte[(int)entry.getSize()];
                    z.read(content, 0, content.length);
                    String s = new String(content, StandardCharsets.UTF_8);
                    String[] lines = s.split("\\r?\\n");
                    int lineCount=1;
                    for(String l:lines){
                        boolean searching = true;
                        String lowerLine = l.toLowerCase();
                        if(lowerLine.contains(word.toLowerCase())){
                            int startFrom = 0;
                            String mod = l;
                            while(searching){
                                int index = lowerLine.indexOf(word.toLowerCase(),startFrom);
                                if(index>=0){
                                    mod = mod.substring(0,index)+mod.substring(index,index+word.length()).toUpperCase()+mod.substring(index+word.length());
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
            
            
            
        }
        catch(IOException e){
               System.out.print("erroro"); 
        }
    }
}