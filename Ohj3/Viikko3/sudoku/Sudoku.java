/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Sudoku {

    /**
     * @param args the command line arguments
     */
    private static HashMap<Integer, HashMap<Integer, Character>> sudoku = new HashMap<>();
    
    
    public Sudoku(){
        
        
        for(int i = 0;i<9;i++){
            int row = i;
            HashMap<Integer, Character> pos = new HashMap<>();
            for(int j = 0;j<9;j++){
                
                int column = j;
                pos.put(column, ' ');
                
                
            }
            sudoku.put(row, pos);
        }
    }
    public static void set(int i, int j, char c){
        if(check_input(i, j)){
            if(Character.isDigit(c) || c == ' '){
                
                sudoku.get(i).put(j, c);
            }
            else{
                System.out.println("Trying to set illegal character " + c + ""
                        + " to (" + i +", "+j+")!");
            }
        }
        else{
            System.out.println("Trying to access illegal cell"
                    + " (" + i +", "+j+")!");
        }
    }
    private static boolean check_input(int i, int j){
        return 0<=i && i<=8 && 0<=j && j<=8;
    }
    public static boolean check(){
        for(int i = 0; i<9; i++){
            ArrayList<Character> numbers = new ArrayList<>();
            ArrayList<Integer> doubles = new ArrayList<>();
            for(int j = 0; j<9; j++){
                if(numbers.contains(sudoku.get(i).get(j))){
                    doubles.add(Character.getNumericValue(sudoku.get(i).get(j)));
                }
                else{
                    if(sudoku.get(i).get(j) != ' '){
                        numbers.add(sudoku.get(i).get(j));
                    }
                     
                }
                
            }
            if(doubles.size()>0){
                Collections.sort(doubles);
                int number = doubles.get(0);
                System.out.println("Row "+i+" has multiple "+number+"'s!");
                return false;
            }
            
        }
        for(int i = 0; i<9; i++){
            ArrayList<Character> numbers = new ArrayList<>();
            ArrayList<Integer> doubles = new ArrayList<>();
            for(int j = 0; j<9; j++){
                if(numbers.contains(sudoku.get(j).get(i))){
                    doubles.add(Character.getNumericValue(sudoku.get(j).get(i)));
                }
                else{
                    if(sudoku.get(j).get(i) != ' '){
                        numbers.add(sudoku.get(j).get(i));
                    } 
                }
                
            }
            if(doubles.size()>0){
                Collections.sort(doubles);
                int number = doubles.get(0);
                System.out.println("Column "+i+" has multiple "+number+"'s!");
                return false;
            }
            
        }
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(!check_block(i, j)){
                   return false; 
                }
            }
        }
        return true;   
    }
    private static boolean check_block(int i, int j){
        ArrayList<Character> numbers = new ArrayList<>();
        ArrayList<Integer> doubles = new ArrayList<>();
        for(int k=i*3;k<i*3+3;k++){
            for(int l=j*3;l<j*3+3;l++){
                if(numbers.contains(sudoku.get(k).get(l))){
                    doubles.add(Character.getNumericValue(sudoku.get(k).get(l)));
                }
                else{
                    if(sudoku.get(k).get(l) != ' '){
                        numbers.add(sudoku.get(k).get(l));
                    }
                }  
            }
        }
        if(doubles.size()>0){
            Collections.sort(doubles);
            int number = doubles.get(0);
            int posy = i*3;
            int posx = j*3;
            System.out.println("Block at ("+posy+", "+posx+") has multiple "+number+"'s!");
            return false;
        }
   
        return true;
    }
    public static void print(){
        
        for(int i=0;i<3;i++){
            printChar('#',37);
            System.out.print('\n');
            for(int j=i*3;j<i*3+3;j++){
                System.out.format("# %c | %c | %c # %c | %c | %c # %c | %c |"
                        + " %c #%n", sudoku.get(j).get(0), sudoku.get(j).get(1),
                        sudoku.get(j).get(2),sudoku.get(j).get(3),
                        sudoku.get(j).get(4),sudoku.get(j).get(5),
                        sudoku.get(j).get(6), sudoku.get(j).get(7),
                        sudoku.get(j).get(8));
                if(j!=2 && j!=5 && j!=8){
                    for(int k = 0; k<3;k++){
                        printChar('#',1);
                        printChar('-',3);
                        printChar('+',1);
                        printChar('-',3);
                        printChar('+',1);
                        printChar('-',3);
                    }
                    printChar('#',1);
                    System.out.print('\n');
                }
            }
        }
        printChar('#',37);
        System.out.print('\n');
    }
    private static void printChar(char c, int width) {
    for(int i = 0; i < width; i++) 
    { System.out.print(c); }
    }
}
