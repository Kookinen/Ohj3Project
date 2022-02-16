/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.*;


public class MovieAnalytics {
    private ArrayList<Movie> movies;
    
    
    
    public MovieAnalytics(){
        movies = new ArrayList<>();
    }
    
    public static Consumer<Movie> showInfo(){
        Consumer<Movie> cons = (Movie t)->{
             System.out.format("%s (By %s, %d)%n", t.getTitle(), t.getDirector(), t.getReleaseYear());
        };
        return cons;
    }
    
    public void populateWithData(String fileName)throws IOException{
        try(var br = new BufferedReader(new FileReader(fileName))){
            String line = null;
            while((line = br.readLine())!= null){
                String[] arr = line.split(";");
                int year = Integer.parseInt(arr[1]);
                int duration = Integer.parseInt(arr[2]);
                double score = Double.parseDouble(arr[4]);
                movies.add(new Movie(arr[0],year,duration,arr[3],score,arr[5]));
            }
            
        }
    }
    public Stream<Movie> moviesAfter(int year){
        Comparator<Movie> comparator = (m1, m2)->m1.getTitle().compareTo(m2.getTitle());
        Comparator<Movie> comparator2 = (m1, m2)->Integer.compare(m1.getReleaseYear(),m2.getReleaseYear());
        Stream<Movie> movieStream = movies.stream().filter(m->m.getReleaseYear()
                >= year).sorted(comparator).sorted(comparator2);
        return movieStream;
    }
    public Stream<Movie> moviesBefore(int year){
        Comparator<Movie> comparator = (m1, m2)->m1.getTitle().compareTo(m2.getTitle());
        Comparator<Movie> comparator2 = (m1, m2)->Integer.compare(m1.getReleaseYear(),m2.getReleaseYear());
        Stream<Movie> movieStream = movies.stream().filter(m->m.getReleaseYear()
                <= year).sorted(comparator).sorted(comparator2);
        return movieStream;
    }
    public Stream<Movie> moviesBetween(int yearA, int yearB){
        Comparator<Movie> comparator = (m1, m2)->m1.getTitle().compareTo(m2.getTitle());
        Comparator<Movie> comparator2 = (m1, m2)->Integer.compare(m1.getReleaseYear(),m2.getReleaseYear());
        
        if(yearA > yearB){
            Stream<Movie> movieStream = movies.stream().filter(m->m.getReleaseYear()
                >= yearB && m.getReleaseYear() <= yearA).sorted(comparator).sorted(comparator2);
            return movieStream;
        }
        else if(yearA < yearB){
            Stream<Movie> movieStream = movies.stream().filter(m->m.getReleaseYear()
                >= yearA && m.getReleaseYear() <= yearB).sorted(comparator).sorted(comparator2);
            return movieStream;
        }
        else{
            Stream<Movie> movieStream = movies.stream().filter(m->m.getReleaseYear()
                == yearA).sorted(comparator).sorted(comparator2);
            return movieStream;
        }
        
    }
     public Stream<Movie> moviesByDirector(String director){
        Comparator<Movie> comparator = (m1, m2)->m1.getTitle().compareTo(m2.getTitle());
        Comparator<Movie> comparator2 = (m1, m2)->Integer.compare(m1.getReleaseYear(),m2.getReleaseYear());
        Stream<Movie> movieStream = movies.stream().filter(m->m.getDirector().equals(director)).sorted(comparator).sorted(comparator2);
        return movieStream;
    }
    
    
}
