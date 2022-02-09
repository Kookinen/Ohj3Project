
import java.util.Iterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayNode extends Node implements Iterable<Node>  {
    
    private ArrayList<Node> list; 
    
    
            
    @Override
    public Iterator<Node> iterator() {
        Iterator<Node> it = new Iterator<>(){
            private Node nextNode = list.get(0);
            private int currentIndex = 0;
            @Override
            public boolean hasNext(){
                return currentIndex != list.size();
            }
            @Override
            public Node next(){
                if(currentIndex == list.size()){
                    currentIndex = 0;
                    throw new NoSuchElementException("no more"); 
                }
                else{
                    Node item = nextNode;
                    if(currentIndex<list.size()-1){
                        nextNode = list.get(currentIndex+1);
                    }
                    currentIndex+=1;
                    return item;
                }
            }
        };
        return it;
    }

    public ArrayNode(){
       list = new ArrayList<>();
    }
    public void add(Node node){
        list.add(node);
    }
    public int size(){
        return list.size();
    }
    
}
