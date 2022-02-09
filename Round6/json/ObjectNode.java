/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Collections;
public class ObjectNode extends Node implements Iterable<String>{
    
    private TreeMap<String, Node> jsonMap;
    private ArrayList<String> keyList = new ArrayList<>();
    
    public ObjectNode(){
        jsonMap = new TreeMap<>();
    }
    public Node get(String key){
        if(jsonMap.containsKey(key)){
            return jsonMap.get(key);
        }
        else{
            return null;
        }
    }
    public void set(String key, Node node){
        jsonMap.put(key, node);
        keyList.add(key);
        Collections.sort(keyList);
    }
    public int size(){
        return keyList.size();
    }

    @Override
    public Iterator<String> iterator() {
        Iterator<String> it = new Iterator<>(){
            private int currentIndex = 0;
            private String nextStr = keyList.get(0);
            @Override
            public boolean hasNext(){
                return currentIndex < keyList.size() && keyList.get(currentIndex)!= null;
            }
            @Override
            public String next(){
                if(nextStr == null){
                    currentIndex = 0;
                    throw new NoSuchElementException("No more values");
                }
                else{
                    String item = keyList.get(currentIndex);
                    if(currentIndex < keyList.size()-1){
                        nextStr = keyList.get(currentIndex+1);
                    }
                    currentIndex +=1;
                    return item;
                }
            }
        };
        return it;
    }
}
