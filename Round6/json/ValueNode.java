/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
public class ValueNode extends Node{
    private double dValue;
    private boolean bValue;
    private String sValue;
    private boolean isDouble = false;
    private boolean isBool = false;
    private boolean isStr = false;
    
    
    public ValueNode(double value){
        this.dValue = value;
        isDouble = true;
    }
    public ValueNode(boolean value){
        this.bValue = value;
        isBool = true;
    }
    public ValueNode(String value){
        this.sValue = value;
        isStr = true;
    }
    public boolean isNumber(){
        return isDouble;
    }
    public boolean isBoolean(){
        return isBool;
    }
    public boolean isString(){
        if(isStr && sValue != null){
            return isStr;
        }
        else{
            return false;
        }
    }
    public boolean isNull(){
        if(sValue == null){
            return true;
        }
        else{
            return false;
        }
    }
    public double getNumber(){
        return dValue;
    }
    public boolean getBoolean(){
        return bValue;
    }
    public String getString(){
        return sValue;
    }
}
