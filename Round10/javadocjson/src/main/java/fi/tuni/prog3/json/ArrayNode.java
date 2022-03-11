/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */


package fi.tuni.prog3.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * A class for representing a JSON array.
 */
public final class ArrayNode extends Node implements Iterable<Node>  {
    
    private ArrayList<Node> list; 
    
    /**
     * Constructs an initially empty JSON array node.
     */
    public ArrayNode(){
       list = new ArrayList<>();
    }
    /**
     * Returns the number of JSON nodes stored in this JSON array.
     * @return the number of JSON nodes in this JSON array.
     */
    public int size(){
        return list.size();
    }
    /**
     * Adds a new JSON node to the end of this JSON array.
     * @param node the new JSON node to be added.
     */
    public void add(Node node){
        list.add(node);
    }
    /**
     * Returns a Node iterator that iterates the JSON nodes stored in this JSON
     * array.
     * @return a Node iterator that iterates the JSON nodes stored in
     * this JSON array.
     */  
    @Override
    public Iterator<Node> iterator() {
        Iterator<Node> it = new Iterator<>(){
            private Node nextNode;
            private int currentIndex = 0;
            @Override
            public boolean hasNext(){
                return currentIndex != list.size();
            }
            @Override
            public Node next(){
                nextNode = list.get(currentIndex);
                if(currentIndex == list.size()){
                    currentIndex = 0;
                    throw new NoSuchElementException("no more"); 
                }
                else{
                    Node item = nextNode;
                    currentIndex+=1;
                    return item;
                }
            }
        };
        return it;
    }
}