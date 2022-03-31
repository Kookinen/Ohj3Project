/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.wordle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Joni
 */
public class Wordle extends Application {
    int wordIndex = 0;
    String word;
    Game game;
    
    
    @Override
    public void start(Stage stage) throws IOException{
    ArrayList<String> wordlist = getWords();
    
    stage.setTitle("Wordle");
    GridPane grid = new GridPane();
    Scene scene = new Scene(grid, 500, 500);
    grid.setBackground(new Background(new BackgroundFill(Color.rgb(30,30,30), CornerRadii.EMPTY, Insets.EMPTY)));
    stage.setScene(scene);
    
    
    Button startGame = new Button("Start new game");
    startGame.setId("newGameBtn");
    grid.add(startGame, 0,0);
    
    Label infoLabel = new Label("");
    infoLabel.setFont(new Font("Arial", 20));
    infoLabel.setTextFill(Color.WHITE);
    infoLabel.setId("infoBox");
    grid.add(infoLabel, 1, 0,2,1);
    
    
    
    startGame.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            infoLabel.setText("");
            word = wordlist.get(wordIndex);
            if(grid.getChildren().size()>2){
                grid.getChildren().remove(2);
            }
            game = new Game(word, infoLabel);
            grid.add(game.addTileToGrid(),1,1);
            wordIndex += 1; 
        }
    });
    
    stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent k){
                    game.HandleKey(k);
        }

        private boolean checkWord() {
            return game.checkWord();
        }
    });

    stage.show();
    

    }
    public static void main(String args[]) {
        launch();
    }

    private ArrayList<String> getWords() throws IOException{
        ArrayList<String> words = new ArrayList<>();
        try(var input = new BufferedReader(new FileReader("words.txt"))){
            String line = "";
            while((line = input.readLine()) != null){
                words.add(line);
            }
        }
        return words;
    }

    

    
}
