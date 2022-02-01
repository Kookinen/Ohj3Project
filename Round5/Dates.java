/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Dates {

    public static class DateDiff {
        private String start;
        private String end;
        private int diff;
        private DateDiff(String start, String end){
            this.start=start;
            this.end=end;
            this.diff = compareDates(start, end);
        }
        public String getStart(){
           String day = start.substring(6);
           String month = start.substring(4,6);
           String year = start.substring(0,4);
           String startStr = String.format("%s-%s-%s", year, month, day);
           return startStr;
        }
        public String getEnd(){
           String day = end.substring(6);
           String month = end.substring(4,6);
           String year = end.substring(0,4);
           String endStr = String.format("%s-%s-%s", year, month, day);
           return endStr;
        }
        public int getDiff(){
            return diff;
        }
        @Override
        public String toString(){
            if(getDiff()==1){
                String wholeStr = String.format("%s %s -> %s %s: %d day",
                        getWeekDay(start), getFin(start), getWeekDay(end), 
                        getFin(end), getDiff());
                return wholeStr;
            }
            else{
                String wholeStr = String.format("%s %s -> %s %s: %d days",
                        getWeekDay(start), getFin(start), getWeekDay(end), 
                        getFin(end), getDiff());
                return wholeStr;
            }
        }
        private String getFin(String str){
           String day = str.substring(6);
           String month = str.substring(4,6);
           String year = str.substring(0,4);
           String startStr = String.format("%s.%s.%s", day, month, year);
           return startStr;
        }
        
        private static String getWeekDay(String date){
            String day = date.substring(6);
            String month = date.substring(4,6);
            String year = date.substring(0,4);
            LocalDate localDate = LocalDate.of(Integer.parseInt(year),
                Integer.parseInt(month),Integer.parseInt(day));
            return localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
        }
            
        private static int compareDates(String date1, String date2){
        String day1 = date1.substring(6);
        String month1 = date1.substring(4,6);
        String year1 = date1.substring(0,4);         
        String day2 = date2.substring(6);
        String month2 = date2.substring(4,6);
        String year2 = date2.substring(0,4);
        
        LocalDate localDate1 = LocalDate.of(Integer.parseInt(year1),
                Integer.parseInt(month1),Integer.parseInt(day1));
        LocalDate localDate2 = LocalDate.of(Integer.parseInt(year2),
                Integer.parseInt(month2),Integer.parseInt(day2));
        long diff = ChronoUnit.DAYS.between(localDate1, localDate2);
        return (int)diff;
        }
        
    }
    public static DateDiff[] dateDiffs(String ...dateStrs){
        int day;
        int month;
        int year;
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<DateDiff> chart= new ArrayList<>();
        
        for(String s:dateStrs){
            if(s.charAt(2)=='.' || s.charAt(1)=='.'){
                String[] splitS = s.split("[.]");
                
                day = Integer.parseInt(splitS[0]);
                month = Integer.parseInt(splitS[1]);
                year = Integer.parseInt(splitS[2]);
                if(checkDate(day,month,year)){
                    String dayStr = Integer.toString(day);
                    String monthStr = Integer.toString(month);
                    if(dayStr.length() == 1){
                        dayStr = String.format("0%s", dayStr);
                    }
                    if(monthStr.length() == 1){
                        monthStr = String.format("0%s", monthStr);
                    }
                    dateList.add(Integer.toString(year)+monthStr+dayStr);
                }
                else{
                    System.out.println("The date "+s+" is illegal!");
                }
            }
            else if(s.charAt(4)=='-'){
                String[] splitS = s.split("-");
                day = Integer.parseInt(splitS[2]);
                month = Integer.parseInt(splitS[1]);
                year = Integer.parseInt(splitS[0]);
                if(checkDate(day,month,year)&& splitS[2].length() != 1 && splitS[1].length() != 1){
                    String dayStr = Integer.toString(day);
                    String monthStr = Integer.toString(month);
                    if(dayStr.length() == 1){
                        dayStr = String.format("0%s", dayStr);
                    }
                    if(monthStr.length() == 1){
                        monthStr = String.format("0%s", monthStr);
                    }
                    dateList.add(Integer.toString(year)+monthStr+dayStr);
                }
                else{
                    System.out.println("The date "+s+" is illegal!");
                }
            }
            else{
                System.out.println("The date "+s+" is illegal!");
            }
            
        }
        Collections.sort(dateList);
        
        for(int i = 0; i<dateList.size()-1; i++){   
            DateDiff df = new DateDiff(dateList.get(i), dateList.get(i+1));
            chart.add(df);
        }
        
        
        DateDiff[] diffs = new DateDiff[chart.size()];
        
        
        return chart.toArray(diffs);
    }
    
    
    
    
    
    private static boolean checkDate(int day, int month, int year)throws DateTimeException{
        try{
            
            LocalDate date = LocalDate.of(year,month,day);
            if(year>999 && year <10000){
                return true;
            }
            return false;
        }
        catch(Exception DateTimeException){
            return false;
        }
    }
}
