/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.wordle;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author Joni
 */
public class Game {
    private int index = 0;
    private int rowIndex = 0;
    private TilePane tile;
    Boolean gameActive = false;
    ArrayList<StackPane> squares;
    String word;
    Label infoLabel;
    int rowAdd;
    
    Game(String word, Label infoLabel){
            
            squares = new ArrayList<>();
            gameActive = true;
            rowIndex = 0;
            index = 0;
            rowAdd = rowIndex*word.length();
            squares.clear();
            this.word = word;
            this.infoLabel = infoLabel;
            tile = new TilePane();
            tile.setPrefColumns(word.length());
            tile.setHgap(5);
            tile.setVgap(5);
            
            for(int j = 0; j<6; j++){
                for(int i = 0; i<word.length();i++){
                    StackPane stack = new StackPane();
                    Label lab = new Label("");
                    lab.setFont(new Font("Helvetica", 30));
                    lab.setId(String.format("%d_%d", j, i));
                    stack.getChildren().addAll(new Rectangle(50,50,Color.GREY), lab);
                    tile.getChildren().add(stack);
                    squares.add(stack);
                }
            }
            
            
            
            
            
    }
    public TilePane addTileToGrid(){
        return tile;
    }
    public Boolean gameActive(){
        return gameActive;
    }
    public void setGameActive(Boolean active){
        gameActive = active;
    }
    public ArrayList getSquares(){
        return squares;
    }
    public void HandleKey(KeyEvent k){
        rowAdd = rowIndex*word.length();
        if(gameActive){
            
                    infoLabel.setText("");
                    if(k.getCode().equals(KeyCode.BACK_SPACE)){
                        if(index+rowAdd != 0+rowAdd){
                            index--;
                            Label l = (Label) squares.get(index+rowAdd).getChildren().get(1);
                            l.setText("");
                        }
                    }
                    else if(k.getCode().equals(KeyCode.ENTER)){
                        if(index+rowAdd != word.length()+rowAdd){
                            infoLabel.setText("Give a complete word before "
                                           + "pressing Enter!");
                        }
                        else{
                            if(checkWord()){
                                infoLabel.setText("Congratulations, you won!");
                                setGameActive(false);
                            }
                            else{
                                if(rowIndex == 5){
                                    infoLabel.setText("Game over, you lost!");
                                    setGameActive(false);
                                }
                                else{
                                    rowIndex++;
                                    index=0;
                                }
                                
                            }
                        }
                    }
                    else{
                        if(!k.getText().equals("")){
                            if(index+rowAdd != word.length()+rowAdd){
                            String key = k.getText();
                            
                            Label l = (Label) squares.get(index+rowAdd).getChildren().get(1);
                            l.setText(key.toUpperCase());
                            index ++;
                            }
                        } 
                    }
                    k.consume();
            }
                    else{
                        k.consume();
                    }
    }
    public Boolean checkWord(){
            rowAdd = rowIndex*word.length();
            ArrayList<Integer> correctPlaces = new ArrayList<>();
            ArrayList<Integer> correctChars = new ArrayList<>();
            for(int i= 0;i<word.length();i++){
                Rectangle r = (Rectangle) squares.get(i+rowAdd).getChildren().get(0);
                r.setFill(Color.GREY);
                Label l = (Label) squares.get(i+rowAdd).getChildren().get(1);
                l.setTextFill(Color.WHITE);
                String s = l.getText();
                if(word.toUpperCase().contains(s.toUpperCase())){
                    correctChars.add(i);
                }
                if(word.toUpperCase().charAt(i) == s.toUpperCase().charAt(0)){
                    correctPlaces.add(i);
                }
            }
            
            for(int i=0;i<correctChars.size();i++){
                Rectangle r = (Rectangle) squares.get(correctChars.get(i)+rowAdd).getChildren().get(0);
                r.setFill(Color.ORANGE);
                Label l = (Label) squares.get(correctChars.get(i)+rowAdd).getChildren().get(1);
                l.setTextFill(Color.WHITE);
            }
            for(int i=0;i<correctPlaces.size();i++){
                Rectangle r = (Rectangle) squares.get(correctPlaces.get(i)+rowAdd).getChildren().get(0);
                r.setFill(Color.GREEN);
                Label l = (Label) squares.get(correctPlaces.get(i)+rowAdd).getChildren().get(1);
                l.setTextFill(Color.WHITE);
            }
            return correctPlaces.size() == word.length();
    }
}
