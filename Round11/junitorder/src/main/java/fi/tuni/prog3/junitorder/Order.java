/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.junitorder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Joni
 */
public class Order {
    
    public static class Item{
        private String name;
        private Double price;
        public Item(String name, double price)throws IllegalArgumentException{
            if(name == null || price < 0){
                throw new IllegalArgumentException("Illegal name or price!");
            }
            this.name = name;
            this.price = price;
        }
        public String getName(){
            return name;
        }
        public double getPrice(){
            return price;
        }
        public String toString(){
            String str = String.format("Item(%s, %.2f)", name, price);
            return str;
        } 
        public boolean equals(Order.Item other){
            return name.equals(other.getName());
        }
    }
    public static class Entry{
        private Order.Item item;
        private int count;
        public Entry(Order.Item item, int count) throws IllegalArgumentException{
            if(count<=0){
                throw new IllegalArgumentException("Illegal count!");
            }
            this.item = item;
            this.count = count;
        }
        public String getItemName(){
            return item.getName();
        }
        public double getUnitPrice(){
            return item.getPrice();
        }
        public Order.Item getItem(){
            return item;
        }
        public int getCount(){
            return count;
        }
        @Override
        public String toString(){
            String str = String.format("%d units of %s", count, item.toString());
            return str;
        }
    }
    ArrayList<Order.Entry> orders;
    public Order(){
        orders = new ArrayList<>();
    }
    
    public boolean addItems(Order.Item item, int count)throws IllegalArgumentException{
        if(count <= 0){
            throw new IllegalArgumentException("Illegal unit count!");
        }
        
        for(Order.Entry e : orders){
            if(e.getItemName().equals(item.getName())){
                if(item.getPrice() != e.getUnitPrice()){
                    throw new IllegalStateException("Illegal price!");
                }
                e.count += count;
                return true;
            }
        }
        orders.add(new Order.Entry(item, count));
        return true;
    }
    public boolean addItems(String name, int count)throws IllegalArgumentException, NoSuchElementException{
        if(count <= 0){
            throw new IllegalArgumentException("Illegal unit count!");
        }
        for(Order.Entry e : orders){
            if(e.getItemName().equals(name)){
                e.count += count;
                return true;
            }
        }
        throw new NoSuchElementException("No such entry with item!");
    }
    public List<Order.Entry> getEntries(){
        return orders;
    }
    public int getEntryCount(){
        return orders.size();
    }
    public int getItemCount(){
        int c = 0;
        for(Order.Entry e:orders){
            c += e.getCount();
        }
        return c;
    }
    public double getTotalPrice(){
        double c = 0;
        for(Order.Entry e:orders){
            c += e.getUnitPrice()*e.getCount();
        }
        return c;
    }
    public boolean isEmpty(){
        return orders.size() == 0;
    }
    public boolean removeItems(String name, int count)throws IllegalArgumentException, NoSuchElementException{
        if(count <= 0){
            throw new IllegalArgumentException("Illegal unit count!");
        }
        int index = 0;
        for(Order.Entry e : orders){
            if(e.getItemName().equals(name)){
                if(e.getCount()<count){
                    throw new IllegalArgumentException("Illegal unit count!");
                }
                else if(e.getCount()==count){
                    orders.remove(index);
                    return true;
                }
                else{
                    e.count -= count;
                    return true;
                }
            }
            index += 1;
        }
        throw new NoSuchElementException("No such element in List!");
    }
}
