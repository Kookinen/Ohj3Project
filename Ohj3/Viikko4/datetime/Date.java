/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
public class Date {
    private int year;
    private int month;
    private int day;
    static boolean isLeapYear(int year) {
        return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }
    static int[][] mDays = {{31, 31}, {28, 29}, {31, 31}, {30, 30}, {31, 31},
        {30, 30}, {31, 31}, {31, 31}, {30, 30}, {31, 31}, {30, 30}, {31, 31}};
    static int monthDays(int month, int year) {
        int days = -1;
        if(1 <= month && month <= 12) {
            // Ehdollinen operaattori kuin C-kieless�.
            days = isLeapYear(year) ? mDays[month-1][1] : mDays[month-1][0];
        }
        return days;
    }
    static boolean isLegalDate(int day, int month, int year) {
        return (1 <= day) && (day <= monthDays(month, year));  
    }
    
    public Date(int year, int month, int day)throws DateException{
        if(!isLegalDate(day, month, year)){
            throw new DateException(String.format("Illegal date %2d.%2d.%2d",
                    day, month, year));
        }
        else{
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
    
    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public String toString(){
        String str = String.format("%2d.%2d.%d",day,month,year);
        return str;
    }
}
