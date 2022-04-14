package fi.tuni.prog3.sisu;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.scene.layout.StackPane;

public class mainWindow {
    TabPane tabs;
    String searchId = "";
    public mainWindow(){
        
        TabPane tabs = new TabPane();
        Label testi = new Label("testitestitesti");
        Tab studentInfo = new Tab("student", testi);
        Tab studies = new Tab("Studies structure", new Label("testitesti"));
        

        StackPane test = new StackPane();

        studentInfo.setContent(test);
        

        tabs.getTabs().addAll(studentInfo, studies);
        this.tabs = tabs; 
   
    }


    public TabPane getTabs(){
        return tabs;
    }

    
}
