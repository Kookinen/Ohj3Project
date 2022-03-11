/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.standings;
/**
 *
 * @author Joni
 */

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 * A class for maintaining team statistics and standings. Team standings are
 * determined by the following rules:
 * <p>
 * <ul>
 *   <li>Primary rule: points total. Higher points come first.</li>
 *   <li>Secondary rule: goal difference (scored minus allowed). Higher
 *       difference comes first.</li>
 *   <li>Tertiary rule: number of goals scored. Higher number comes first.</li>
 *   <li>Last rule: natural String order of team names.</li>
 * </ul>
 */
public class Standings {
    /**
     * A class for storing statistics of a single team. The class offers only
     * public getter functions. The enclosing class Standings is responsible for
     * setting and updating team statistics.
     */
    public static class Team{
        private final String TeamName;
        private int Wins = 0;
        private int Losses = 0;
        private int Ties = 0;
        private int Scored = 0;
        private int Allowed = 0;
        private int Points = 0;
        /**
         * Constructs a Team object for storing statistics of the named team.
         * @param name the name of the team whose statistics the new team object
         * stores.
         */
        public Team(String name){
            TeamName = name;
        }
        /**
         * Returns the number of wins of the team.
         * @return the number of wins of the team.
         */
        public int getWins(){
            return Wins;
        }
        /**
         * Returns the number of ties of the team.
         * @return the number of ties of the team.
         */
        public int getTies(){
            return Ties;
        }
        /**
         * Returns the number of losses of the team.
         * @return the number of losss of the team.
         */
        public int getLosses(){
            return Losses;
        }
        /**
         * Returns the number of goals scored by the team.
         * @return the number of goals scored by the team.
         */
        public int getScored(){
            return Scored;
        }
        /**
         * Returns the number of goals allowed (conceded) by the team.
         * @return the number of goals allowed (conceded) by the team.
         */
        public int getAllowed(){
            return Allowed;
        }
        /**
         * Returns the overall number of points of the team.
         * @return the overall number of points of the team.
         */
        public int getPoints(){
            return Points;
        }
        /**
         * Returns the name of the team.
         * @return the name of the team.
         */
        public String getName(){
            return TeamName;
        }
    }
    
    private static HashMap<String, Team> standings;
    /**
     * Constructs an empty Standings object.
     */
    public Standings(){
        standings = new HashMap<>();
    }
    /**
     * Constructs a Standings object that is initialized with the game data read
     * from the specified file. The result is identical to first constructing an
     * empty Standing object and then calling 
     * {@link #readMatchData(java.lang.String) readMatchData(filename)}.
     * @param filename the name of the game data file to read.
     * @throws IOException if there is a problem reading the
     */
    public Standings(String filename)throws IOException{
        standings = new HashMap<>();
        readFile(filename);
        
    }
    /**
     * Reads game data from the specified file and updates the team statistics
     * and standings accordingly.
     * <p>
     * The match data file is expected to contain lines of form
     * "teamNameA\tgoalsA-goalsB\tteamNameB". Note that the '\t' are tabulator
     * characters.
     * <p>
     * E.g. the line "Iceland\t3-2\tFinland" would describe a match between
     * Iceland and Finland where Iceland scored 3 and Finland 2 goals.
     * @param filename the name of the game data file to read.
     * @throws IOException if there is some kind of an IO error (e.g. if the specified file does not exist).
     */
    public void readMatchData(String filename)throws IOException{
        readFile(filename);
    }
    /**
     * Updates the team statistics and standings according to the match result
     * described by the parameters.
     * @param teamNameA the name of the first ("home") team.
     * @param goalsA the number of goals scored by the first team.
     * @param goalsB the number of goals socred by the second team.
     * @param teamNameB the name of the second ("away") team.
     */
    public void addMatchResult(String teamNameA, int goalsA, int goalsB, 
            String teamNameB){
        if(!standings.containsKey(teamNameA)){
                    Team x = new Team(teamNameA);
                    standings.put(teamNameA, x);
                }
                if(!standings.containsKey(teamNameB)){
                    Team x = new Team(teamNameB);
                    standings.put(teamNameB, x);
                }
                int score1=goalsA;
                int score2=goalsB;
                standings.get(teamNameA).Scored += score1;
                standings.get(teamNameA).Allowed += score2;
                standings.get(teamNameB).Scored += score2;
                standings.get(teamNameB).Allowed += score1;
                
                if(score1>score2){
                    standings.get(teamNameA).Wins += 1;
                    standings.get(teamNameB).Losses += 1;
                    standings.get(teamNameA).Points += 3;
                }
                else if(score2>score1){
                    standings.get(teamNameB).Wins += 1;
                    standings.get(teamNameA).Losses += 1;
                    standings.get(teamNameB).Points += 3;
                }
                else{
                    standings.get(teamNameB).Ties += 1;
                    standings.get(teamNameA).Ties += 1;
                    standings.get(teamNameB).Points += 1;
                    standings.get(teamNameA).Points += 1;
                }
    }
    /**
     * Returns a list of the teams in the same order as they would appear in a 
     * standings table.
     * @return a list of the teams in the same order as they would appear in a 
     * standings table.
     */
    public List<Team> getTeams(){
        ArrayList<String> teams = new ArrayList<>(standings.keySet());
        ArrayList<Team> orderTeams = new ArrayList<>();
        
        
        while(teams.size() > 0){
            ArrayList<String> points = new ArrayList<>();
            int greatest = -1;
            String greatestTeam = "";
            int greatestIndex = 0;
            for(int i = 0; i<teams.size(); i++){
                
                int team1 = standings.get(teams.get(i)).getPoints();
                if(team1 > greatest){
                    greatestTeam = teams.get(i);
                    greatest = team1;
                    greatestIndex = i;
                }
            
            }
            teams.remove(greatestIndex);
            points.add(greatestTeam);
            for(int i = 0; i<teams.size(); i++){
                int team1 = standings.get(teams.get(i)).getPoints();
                if(team1 == greatest){
                    points.add(teams.get(i));
                    teams.remove(i);
                    i--;
                }
            }
            if(points.size()>1){
                while(points.size() > 0){
                    ArrayList<String> goalDif = new ArrayList<>();
                    int difference = -99;
                    String greatestTeamDif = "";
                    int difIndex = 0;
                    for(int i = 0; i<points.size(); i++){
                        int team1 = standings.get(points.get(i)).getScored()-
                            standings.get(points.get(i)).getAllowed();
                        if(team1>difference){
                            difference = team1;
                            greatestTeamDif = points.get(i);
                            difIndex = i;
                        }
                    }
                    points.remove(difIndex);
                    goalDif.add(greatestTeamDif);
                    for(int i = 0;i<points.size();i++){
                        int team1 = standings.get(points.get(i)).getScored()-
                            standings.get(points.get(i)).getAllowed();
                        if(team1 == difference){
                            goalDif.add(points.get(i));
                            points.remove(i);
                            i--;
                        }
                    }
                    if(goalDif.size() > 1){
                        while(goalDif.size() > 0){
                            ArrayList<String> goals = new ArrayList<>();
                            int mostGoals = -1;
                            String greatestGoal = "";
                            int goalIndex = 0;
                            for(int i = 0; i<goalDif.size(); i++){
                                int team1 = standings.get(goalDif.get(i)).getScored();
                                if(team1>mostGoals){
                                       mostGoals = team1;
                                       greatestGoal = goalDif.get(i);
                                       goalIndex = i;
                                   }
                               }
                               goalDif.remove(goalIndex);
                               goals.add(greatestGoal);
                               for(int i = 0;i<goalDif.size();i++){
                                   int team1 = standings.get(goalDif.get(i)).getScored();
                                   if(team1 == difference){
                                       goals.add(goalDif.get(i));
                                       goalDif.remove(i);
                                       i--;
                                   }
                               }
                               if(goals.size()>1){
                                   while(goals.size() > 0){
                                       Collections.sort(goals);
                                       for(int i = 0; i<goals.size();i++){
                                           orderTeams.add(standings.get(goals.get(i)));
                                       }
                                       goals.clear();
                                   }
                               }
                               else{
                                   orderTeams.add(standings.get(greatestGoal));
                               }
                        }
                    }
                    else{
                        orderTeams.add(standings.get(greatestTeamDif));
                    }
                }
                
            }
            else{
                orderTeams.add(standings.get(greatestTeam));
            }
        }
    return orderTeams;        
    }
    /**
     * Prints a formatted standings table to the provided output stream.
     * @param out the output stream to use when printing the standings table.
     */
    public void printStandings(PrintStream out){
        List<Team> orderTeams = getTeams();
        int longest = 0;
        for(int i = 0;i<orderTeams.size();i++){
            if(orderTeams.get(i).TeamName.length()>longest){
                longest = orderTeams.get(i).TeamName.length();
            }
        }
        for(int i = 0;i<orderTeams.size();i++){
            Team team = orderTeams.get(i);
            System.out.format("%-"+longest+"s %3d %3d %3d %3d %6s %3d%n", team.TeamName,
                    team.getLosses()+team.getTies()+team.getWins(), team.getWins(),
                    team.getTies(), team.getLosses(),team.getScored()+"-"+team.getAllowed(), team.getPoints());
        }
        
    }
    
    
    /**
     * @hidden 
     */
    private void readFile(String name)throws IOException{
        try(var input = new BufferedReader(new FileReader(name))){
            String line;
            while((line = input.readLine()) != null){
                String[] words = line.split("\\t");
                String[] strScores = words[1].split("-");
                addMatchResult(words[0], Integer.parseInt(strScores[0]),
                        Integer.parseInt(strScores[1]), words[2]);
            }
        }
    }
}
