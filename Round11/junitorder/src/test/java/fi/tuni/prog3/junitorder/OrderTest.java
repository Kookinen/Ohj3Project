/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.junitorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Joni
 */
public class OrderTest {
    
    @Test
    public void testOrderItemConstruct(){
        Order.Item item =  new Order.Item("maito", 2.3);
        String expName = "maito";
        double expPrice = 2.3;
        String resultName = item.getName();
        double resultPrice = item.getPrice();
        assertEquals(expName, resultName);
        assertEquals(expPrice, resultPrice);
    }
    
    @Test
    public void testOrderItemException(){
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> new Order.Item("maito", -2));
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> new Order.Item(null, 2.3));
        String expMessage = "Illegal name or price!";
        String actualMessage1 = exception1.getMessage();
        String actualMessage2 = exception2.getMessage();
        assertTrue(actualMessage1.contains(expMessage));
        assertTrue(actualMessage2.contains(expMessage));
    }
    
    @Test
    public void testOrderItemToString(){
        Order.Item item =  new Order.Item("maito", 2.3);
        String expString = "Item(maito, 2.30)";
        String actual = item.toString();
        assertEquals(expString, actual);
    }
    
    @Test
    public void testOrderItemEquals(){
        Order.Item item =  new Order.Item("maito", 2.3);
        Order.Item item2 =  new Order.Item("maitokaakao", 2.4);
        Order.Item item3 =  new Order.Item("maito", 2.3);
        assertTrue(item.equals(item3));
        assertFalse(item.equals(item2));
    }
    
    @Test
    public void testOrderEntryConstruct(){
        Order.Item item = new Order.Item("maito", 2.3);
        Order.Entry entry = new Order.Entry(item, 4);
        Order.Item exp = item;
        int expCount = 4;
        Order.Item actual = entry.getItem();
        int actualCount = entry.getCount();
        assertEquals(exp,actual);
        assertEquals(expCount, actualCount);
    }
    
    @Test
    public void testOrderEntryException(){
        Order.Item item = new Order.Item("maito", 2.3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order.Entry(item, -2));
        String expMessage = "Illegal count!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expMessage));
    }
    
    @Test
    public void testOrderEntryToString(){
        Order.Item item = new Order.Item("maito", 2.3);
        Order.Entry entry = new Order.Entry(item, 4);
        String exp = "4 units of Item(maito, 2.30)";
        String actual = entry.toString();
        assertEquals(exp, actual);
    }
    @Test
    public void testOrderEntryGetItemName(){
        Order.Item item = new Order.Item("maito", 2.3);
        Order.Entry entry = new Order.Entry(item, 4);
        String exp = "maito";
        String actual = entry.getItemName();
        assertEquals(exp, actual);
    }
    @Test
    public void testOrderEntryGetUnitPrice(){
        Order.Item item = new Order.Item("maito", 2.3);
        Order.Entry entry = new Order.Entry(item, 4);
        double exp = 2.3;
        double actual = entry.getUnitPrice();
        assertEquals(exp, actual);
    }
    
    @Test
    public void testOrderAddItems(){
        Order order = new Order();
        ArrayList<Order.Entry> orders = new ArrayList<>();
        Order.Item item = new Order.Item("maito", 2.3);
        assertTrue(order.addItems(item,4));
        
    }
    
    @Test
    public void testOrderException(){
        Order order = new Order();
        Order.Item item = new Order.Item("maito", 2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> order.addItems(item, -4));
        String expMessage = "Illegal unit count!";
        String actualMessage1 = exception.getMessage();
        assertTrue(actualMessage1.contains(expMessage));
        Order.Item item2 = new Order.Item("maito", 3);
        order.addItems(item, 4);
        Exception exception2 = assertThrows(IllegalStateException.class, () -> order.addItems(item2, 4));
        String expMessage2 = "Illegal price!";
        String actualMessage2 = exception2.getMessage();
        assertTrue(actualMessage2.contains(expMessage2));
    }
    @Test
    public void testAddItemsName(){
        Order order = new Order();
        ArrayList<Order.Entry> orders = new ArrayList<>();
        Order.Item item = new Order.Item("maito", 2);
        order.addItems(item,4);
        String name = "maito";
        assertTrue(order.addItems(name,4));
    }
    @Test
    public void testOrderException2(){
        Order order = new Order();
        ArrayList<Order.Entry> orders = new ArrayList<>();
        Order.Item item = new Order.Item("maito", 2);
        order.addItems(item,4);
        String name = "maito";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> order.addItems(name,-4));
        String expMessage1 = "Illegal unit count!";
        String actualMessage1 = exception.getMessage();
        assertTrue(actualMessage1.contains(expMessage1));
        Exception exception2 = assertThrows(NoSuchElementException.class, () -> order.addItems("kalja",4));
        String expMessage2 = "No such entry with item!";
        String actualMessage2 = exception2.getMessage();
        assertEquals(actualMessage2, expMessage2);
    }
    @Test
    public void testGetEntries(){
        Order order = new Order();
        Order.Item item = new Order.Item("maito", 2);
        order.addItems(item,4);
        Order.Entry entry = new Order.Entry(item,4);
        ArrayList<Order.Entry> expList = new ArrayList<>(Arrays.asList(entry));
        List<Order.Entry> actualList = order.getEntries();
        assertEquals(actualList.get(0).getItemName(), expList.get(0).getItemName());
    }
    @Test
    public void testGetEntryCount(){
        Order order = new Order();
        Order.Item item = new Order.Item("maito", 2);
        order.addItems(item,4);
        Order.Item item2 = new Order.Item("tee", 1.3);
        order.addItems(item2,3);
        int expCount = 2;
        int actual = order.getEntryCount();
        assertEquals(expCount, actual);
    }
    @Test
    public void testGetItemCount(){
        Order order = new Order();
        Order.Item item = new Order.Item("maito", 2);
        order.addItems(item,4);
        Order.Item item2 = new Order.Item("tee", 1.3);
        order.addItems(item2,3);
        int expCount = 7;
        int actual = order.getItemCount();
        assertEquals(expCount, actual);
    }
    @Test
    public void testTotalPrice(){
        Order order = new Order();
        Order.Item item = new Order.Item("maito", 2);
        order.addItems(item,4);
        Order.Item item2 = new Order.Item("tee", 1.3);
        order.addItems(item2,3);
        double expPrice = 11.9;
        double actual = order.getTotalPrice();
        assertEquals(expPrice, actual);
    }
    @Test
    public void testIsEmpty(){
        Order order = new Order();
        Order.Item item = new Order.Item("maito", 2);
        assertTrue(order.isEmpty());
        order.addItems(item,4);
        assertFalse(order.isEmpty());
    }
    @Test 
    public void testRemoveItems(){
        Order order = new Order();
        Order.Item item = new Order.Item("maito", 2);
        order.addItems(item,4);
        Order.Item item2 = new Order.Item("tee", 1.3);
        order.addItems(item2,3);
        assertTrue(order.removeItems("maito", 4));
    }
    @Test
    public void testRemoveException(){
        Order order = new Order();
        Order.Item item = new Order.Item("maito", 2);
        order.addItems(item,4);
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> order.removeItems("maito", -4));
        String expMessage1 = "Illegal unit count!";
        String actualMessage1 = exception1.getMessage();
        assertTrue(actualMessage1.contains(expMessage1));
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> order.removeItems("maito", 5));
        String expMessage2 = "Illegal unit count!";
        String actualMessage2 = exception2.getMessage();
        assertTrue(actualMessage2.contains(expMessage2));
        Exception exception3 = assertThrows(NoSuchElementException.class, () -> order.removeItems("kalja", 3));
        String expMessage3 = "No such element in List!";
        String actualMessage3 = exception3.getMessage();
        assertTrue(actualMessage3.contains(expMessage3));
    }
}
