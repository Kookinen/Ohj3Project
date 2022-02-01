/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
public class DateTime extends Date{
    
    private int hour;
    private int minute;
    private int second;
    
    public DateTime(int year, int month, int day, int hour, int minute,
            int second)throws DateException{
        super(year, month, day);
        if(hour<0 || hour>23 || minute < 0 || minute > 59 || second < 0 || 
                second > 59){
            throw new DateException(String.format("Illegal time %2d:%2d:%2d",
                    hour, minute, second));
        }
        else{
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
        
    }
    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
    }
    public int getSecond(){
        return second;
    }
    public String toString(){
        String strHour = Integer.toString(hour);
        String strMinute = Integer.toString(minute);
        String strSecond = Integer.toString(second);
        if(strHour.length()==1){
            strHour = "0"+strHour;
        }
        if(strMinute.length()==1){
            strMinute = "0"+strMinute;
        }
        if(strSecond.length()==1){
            strSecond = "0"+strSecond;
        }
        String str = String.format(super.toString()+" %2s:%2s:%2s",strHour,strMinute,strSecond);
        return str;
    }
}
