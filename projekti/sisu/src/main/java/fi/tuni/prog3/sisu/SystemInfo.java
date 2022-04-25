package fi.tuni.prog3.sisu;

public class SystemInfo {

    
    /** 
     * @return String
     */
    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    
    /** 
     * @return String
     */
    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

}