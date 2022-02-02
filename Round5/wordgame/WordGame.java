/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordGame {
    
    private ArrayList<String> words = new ArrayList<>();
    private int wordCount;
    private WordGameState gamestate;
    private String wordVis;
    private boolean gameOn = false;
    
    public static class WordGameState {
        private String word = "";
        private int mistakes;
        private int maxMistakes;
        private int unknownLetters;
        
        private WordGameState(String word, int Mistakes){
            this.word = word;
            this.maxMistakes = Mistakes;
            mistakes = 0;
            unknownLetters = word.length();
        }
        
        public String getWord(){
            return word;
        }
        public int getMistakes(){
            return mistakes;
        }
        public int getMistakeLimit(){
            return maxMistakes;
        }
        public int getMissingChars(){
            return unknownLetters;
        }
    }
    
    public WordGame(String wordFilename)throws IOException{
        try(var input = new BufferedReader(new FileReader(wordFilename))){
            String line = null;
            while((line = input.readLine()) != null){
                words.add(line);
            }
            wordCount = words.size();
        }
    }
    public void initGame(int wordIndex, int mistakeLimit){
        wordVis = words.get(wordIndex % wordCount);
        String str = "_";
        str = str.repeat(wordVis.length());        
        gamestate = new WordGameState(str, mistakeLimit);
        gameOn = true;
    } 
    public boolean isGameActive(){
        return gameOn;
    }
    public WordGameState getGameState()throws GameStateException{
        if(isGameActive()){
            return gamestate;
        }
        else{
            throw new GameStateException("There is currently no active word game!");
        }
    }
    public WordGameState guess(char c)throws GameStateException{
        if(isGameActive()){
            String guessChar = ""+c;
            
            if(wordVis.contains(guessChar.toLowerCase()) && !gamestate.word.contains(guessChar.toLowerCase())){
                ArrayList<Integer> indexes = new ArrayList<>();
                for(int i =0;i<wordVis.length();i++){
                    if(wordVis.charAt(i) == Character.toLowerCase(c)){
                        indexes.add(i);
                    }
                }
                String oldWord = gamestate.getWord();
                char[] wordCharArray = oldWord.toCharArray();
                for(int i = 0; i<indexes.size(); i++){
                    wordCharArray[indexes.get(i)] = Character.toLowerCase(c);
                    gamestate.unknownLetters -= 1;
                }
                gamestate.word = String.valueOf(wordCharArray);
                if(gamestate.unknownLetters == 0){
                    gameOn = false;
                }
                return gamestate;
            }
            else{
                gamestate.mistakes += 1;
                if(gamestate.mistakes == 1+gamestate.maxMistakes){
                    gamestate.word = wordVis;
                    gameOn = false;
                }
                return gamestate;
            }
        }
        else{
            throw new GameStateException("There is currently no active word game!");
        }
    }
    public WordGameState guess(String word)throws GameStateException{
        if(isGameActive()){
            if(wordVis.equals(word)){
                gamestate.word = wordVis;
                gamestate.unknownLetters = 0;
                if(gamestate.unknownLetters == 0){
                    gameOn = false;
                }
                return gamestate;
            }
            else{
                
                gamestate.mistakes += 1;
                if(gamestate.mistakes == 1+gamestate.maxMistakes){
                    gamestate.word = wordVis;
                    gameOn = false;
                }
                return gamestate;
            }
        }
        else{
            throw new GameStateException("There is currently no active word game!");
        }
    }
}
