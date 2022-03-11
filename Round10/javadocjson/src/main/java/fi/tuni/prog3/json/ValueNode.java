/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */


package fi.tuni.prog3.json;
/**
 * A class for representing a JSON values. The value can be either a double, a boolean, a String or null.
 */
public final class ValueNode extends Node{
    private double dValue;
    private boolean bValue;
    private String sValue;
    private boolean isDouble = false;
    private boolean isBool = false;
    private boolean isStr = false;
    private Object NullValue;
    
    /**
     * Constructs a JSON value node that stores the given double value.
     * @param value The double value to store in the new JSON value node.
     */
    public ValueNode(double value){
        this.dValue = value;
        isDouble = true;
    }
    /**
     * Constructs a JSON value node that stores the given boolean value.
     * @param value The boolean value to store in the new JSON value node.
     */
    public ValueNode(boolean value){
        this.bValue = value;
        isBool = true;
    }
    /**
     * Constructs a JSON value node that stores the given string or null.
     * @param value The string or null to store in the new JSON value node.
     */
    public ValueNode(String value){
        this.sValue = value;
        isStr = true;
    }
    /**
     * Checks whether this value node stores a number (double).
     * @return true if this node stores a double value, otherwise false.
     */
    public boolean isNumber(){
        return isDouble;
    }
    /**
     * Checks whether this value node stores a boolean value.
     * @return true if this node stores a boolean value, otherwise false.
     */
    public boolean isBoolean(){
        return isBool;
    }
    /**
     * Checks whether this value node stores a string.
     * @return true if this node stores a string, otherwise false.
     */
    public boolean isString(){
        if(isStr && sValue != null){
            return isStr;
        }
        else{
            return false;
        }
    }
     /**
     * Checks whether this value node stores null.
     * @return true if this node stores null, otherwise false.
     */
    public boolean isNull(){
        if(sValue == null){
            return true;
        }
        else{
            return false;
        }
    }
     /**
     * Returns the stored value as a number (double).
     * @return the stored number as a double value.
     * @throws IllegalStateException if the stored value is not a number.
     */
    public double getNumber() {
        try{
            return dValue;
        }
        catch(IllegalStateException t){
            
        }
        return dValue;
    }
    /**
     * Returns the stored value as a boolean value.
     * @return the stored boolean value.
     * @throws IllegalStateException if the stored value is not a boolean value.
     */
    public boolean getBoolean(){
        try{
            return bValue;
        }
        catch(IllegalStateException t){}
        return bValue;
    }
    /**
     * Returns the stored value as a string.
     * @return the stored string.
     * @throws IllegalStateException if the stored value is not a string.
     */
    public String getString(){
        try{
            return sValue;
        }
        catch(IllegalStateException t){}
        return sValue;
    }
    /**
     * Returns the stored value as null.
     * @return null.
     * @throws IllegalStateException if the stored value is not null.
     */
    public Object getNull(){
        try{
            return NullValue;
        }
        catch(IllegalStateException t){}
        return NullValue;
    }
}
