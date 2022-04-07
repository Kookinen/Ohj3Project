/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.sisu;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author Joni
 */
public class mainWindow {
    TabPane tabs;
    public mainWindow(){
        TabPane tabs = new TabPane();
        Label testi = new Label("testitestitesti");
        Tab studentInfo = new Tab("student", testi);
        Tab studies = new Tab("Studies structure", new Label("testitesti"));
        tabs.getTabs().addAll(studentInfo, studies);
        this.tabs = tabs;
    }
    public TabPane getTabs(){
        return tabs;
    }
    
}
