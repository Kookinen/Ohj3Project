
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
public class MovieAnalytics2 {
    private ArrayList<Movie> movies;
    
    public MovieAnalytics2(){
        movies = new ArrayList<>();
    }
    public void populateWithData(String fileName)throws IOException{
        try(var br = new BufferedReader(new FileReader(fileName))){
            List<Movie> movieList = br.lines().map(line->line.split(";")).map(movie->
                    new Movie(movie[0],Integer.parseInt(movie[1]),
                    Integer.parseInt(movie[2]),movie[3],Double.parseDouble(movie[4]),
                    movie[5])).collect(Collectors.toList());    
            movies.addAll(movieList);
        }
    }
    public void printCountByDirector(int n){
        Map<String, Long> directors = movies.stream().collect(Collectors.groupingBy(m->new String(m.getDirector()), Collectors.counting()));
        directors.entrySet().stream().sorted((m1,m2)->m1.getKey().compareTo(m2.getKey())).
                sorted((m1, m2)->Long.compare(m2.getValue(),m1.getValue())).limit(n).
                forEach(s -> System.out.format("%s: %d movies%n", s.getKey(), s.getValue()));
    }
    public void printAverageDurationByGenre(){
        Map<String, Double> genres = movies.stream().collect(Collectors.groupingBy(m->new String(m.getGenre()), Collectors.averagingInt(m->m.getDuration())));
        genres.entrySet().stream().sorted((m1,m2)->m1.getKey().compareTo(m2.getKey())).sorted((m1,m2)->Double.compare(m1.getValue(),m2.getValue())).
                forEach(s->System.out.format("%s: %.2f%n", s.getKey(),s.getValue()));
    }
    public void printAverageScoreByGenre(){
        Map<String, Double> genres = movies.stream().collect(Collectors.groupingBy(m->new String(m.getGenre()), Collectors.averagingDouble(m->m.getScore())));
        genres.entrySet().stream().sorted((m1,m2)->m1.getKey().compareTo(m2.getKey())).sorted((m1,m2)->Double.compare(m2.getValue(),m1.getValue())).
        forEach(s->System.out.format("%s: %.2f%n", s.getKey(),s.getValue()));
    }
}
