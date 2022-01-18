/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
public class Mean {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        double sum = 0;
        for(String s:args){
            sum += Double.parseDouble(s);
        }
        double result = sum/args.length;
        System.out.println("Mean: "+result);
    }
}
