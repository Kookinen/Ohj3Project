/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.junitattainment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Joni
 */
public class AttainmentTest {
    
    
    public AttainmentTest() {
    }
    
    @Test
    public void testAttainment(){
        Attainment att = new Attainment("TIE-231","12345",3);
        String expCode = "TIE-231";
        String expNumber = "12345";
        int expGrade = 3;
        String resultCode = att.getCourseCode();
        String resultNumber = att.getStudentNumber();
        int resultGrade = att.getGrade();
        assertEquals(expCode, resultCode);
        assertEquals(expNumber, resultNumber);
        assertEquals(expGrade, resultGrade);
    }
    
    @Test
    public void testException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Attainment(null, "123", 1));
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> new Attainment("TIE-245", "123", 6));
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> new Attainment("TIE-245", null, 1));
        String expMessage = "Invalid course code, student number or grade!";
        String actualMessage1 = exception.getMessage();
        String actualMessage2 = exception2.getMessage();
        String actualMessage3 = exception3.getMessage();
        assertTrue(actualMessage1.contains(expMessage));
        assertTrue(actualMessage2.contains(expMessage));
        assertTrue(actualMessage3.contains(expMessage));
    }
    
    @Test
    public void testToString(){
        Attainment att = new Attainment("TIE-245", "12345", 3);
        String expected = String.format("%s %s %d", att.getCourseCode(), att.getStudentNumber(), att.getGrade());
        String actual = att.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCompareTo(){
        Attainment att1 = new Attainment("TIE", "12345", 3);
        Attainment att2 = new Attainment("AIE", "12345", 3);
        int result = att1.compareTo(att2);
        int expected = "TIE".compareTo("AIE");
        assertEquals(expected, result);
    }
    
    
}
