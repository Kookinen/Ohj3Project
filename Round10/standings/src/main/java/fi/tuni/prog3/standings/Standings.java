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
import java.util.Collections;
public class Standings {
    
    public static class Team{
        private final String TeamName;
        private int Wins = 0;
        private int Losses = 0;
        private int Ties = 0;
        private int Scored = 0;
        private int Allowed = 0;
        private int Points = 0;
        
        public Team(String name){
            TeamName = name;
        }
        public int getWins(){
            return Wins;
        }
        public int getTies(){
            return Ties;
        }
        public int getLosses(){
            return Losses;
        }
        public int getScored(){
            return Scored;
        }
        public int getAllowed(){
            return Allowed;
        }
        public int getPoints(){
            return Points;
        }
        public String getName(){
            return TeamName;
        }
    }
    
    private static HashMap<String, Team> standings;
    
    public Standings(){
        standings = new HashMap<>();
    }
    public Standings(String filename)throws IOException{
        standings = new HashMap<>();
        readFile(filename);
        
    }
    public static void readMatchData(String filename)throws IOException{
        readFile(filename);
    }
    
    public static void addMatchResult(String teamNameA, int goalsA, int goalsB, 
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
    
    public static List<Team> getTeams(){
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
    
    public static void printStandings(PrintStream a){
        ArrayList<Team> orderTeams = getTeams();
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
    
    
    
    private static void readFile(String name)throws IOException{
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