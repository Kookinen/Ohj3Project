package fi.tuni.prog3.sisu;

import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javafx.scene.layout.StackPane;

public class mainWindow {
    TabPane tabs;
    String searchId = "";
    public mainWindow(HashMap<String, Degree> degrees){
        
        TabPane tabs = new TabPane();
        Label testi = new Label("testitestitesti");
        TextField text = new TextField();
        Button get = new Button("Hae!");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(text, get);
        get.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                if(!"".equals(text.getText())){
                    String name = text.getText();
                    Degree deg = degrees.get(name);
                    if(deg != null){
                        
                        PrintDegree nyhtis = new PrintDegree(deg.getName(), deg.getModules());
                    }
                    else{
                        System.out.println("notFound");
                    }
                    
                }
            }
            
        });
        
        
        Tab studentInfo = new Tab("student", testi);
        Tab studies = new Tab("Studies structure", new Label("testitesti"));
        studies.setContent(hbox);
        

        StackPane test = new StackPane();

        studentInfo.setContent(test);
        

        tabs.getTabs().addAll(studentInfo, studies);
        this.tabs = tabs; 
   
    }


    public TabPane getTabs(){
        return tabs;
    }

    
}
