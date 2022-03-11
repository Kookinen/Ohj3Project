/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */


package fi.tuni.prog3.json;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Collections;
/**
 * A class for representing a JSON object.
 */
public final class ObjectNode extends Node implements Iterable<String>{
    
    private TreeMap<String, Node> jsonMap;
    private ArrayList<String> keyList = new ArrayList<>();
    
    /**
     * Constructs an initially empty JSON object node.
     */
    public ObjectNode(){
        jsonMap = new TreeMap<>();
    }
    /**
     * Returns the number of JSON nodes stored under this JSON object.
     * @return the number of JSON nodes under this JSON object.
     */
    public int size(){
        return keyList.size();
    }
    /**
     * Returns the JSON node stored under the given name.
     * @param name the name of the name-node pair whose node should be returned.
     * @return the JSON node corresponding to name, or null if such node
     * does not exist.
     */
    public Node get(String name){
        if(jsonMap.containsKey(name)){
            return jsonMap.get(name);
        }
        else{
            return null;
        }
    }
    /**
     * Stores a name - JSON node pair into this JSON object. If a name-node pair
     * with the same name already exists, the previously existing node will be
     * replaced.
     * @param name the name of the name-node pair.
     * @param node the JSON node of the name-node pair.
     */
    public void set(String name, Node node){
        jsonMap.put(name, node);
        keyList.add(name);
        Collections.sort(keyList);
    }
    
    /**
     * Returns a String iterator that iterates the names of the name-node pairs
     * stored in this JSON object in natural String order.
     * @return a String iterator that iterates the names of the stored name-node
     * pairs in natural String order.
     */
    @Override
    public Iterator<String> iterator() {
        Iterator<String> it = new Iterator<>(){
            private int currentIndex = 0;
            private String nextStr;
            @Override
            public boolean hasNext(){
                return currentIndex < keyList.size() && keyList.get(currentIndex)!= null;
            }
            @Override
            public String next(){
                nextStr = keyList.get(currentIndex);
                if(nextStr == null){
                    currentIndex = 0;
                    throw new NoSuchElementException("No more values");
                }
                else{
                    String item = keyList.get(currentIndex);
                    
                    currentIndex +=1;
                    return item;
                }
            }
        };
        return it;
    }
}

