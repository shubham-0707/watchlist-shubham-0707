package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Repository
public class MovieRepository {

    private Map<String , Movie> movie_map;
    private Map<String , Director> director_map;
    private Map<String , List<String>> movie_director_map;

    MovieRepository(){
        movie_map = new HashMap<>();
        director_map = new HashMap<>();
        movie_director_map = new HashMap<>();
    }


    public void addMovieToDB(Movie movie){
        movie_map.put(movie.getName() , movie);
    }

    public void addDirectorToDB(Director director){
        director_map.put(director.getName() , director);
    }

    public void addMovieDirectorPair(String movie_name , String director_name){
        if(movie_map.containsKey(movie_name) && director_map.containsKey(director_name)){
            List<String> temp = movie_director_map.get(director_name);
            if(temp==null){
                temp = new ArrayList<>();
                temp.add(movie_name);
                movie_director_map.put(director_name , temp);
            }
            else{
                temp.add(movie_name);
            }
        }
    }

    public Movie getMovieByMovieNameFromDB(String name){
        return movie_map.get(name);
    }

    public Director getDirectorByDirectorNameFromDB(String name){
        return director_map.get(name);
    }

    public List<String> getListOfMoviesOfADirector(String director_name){
        List<String> movies = new ArrayList<>();
        if(movie_director_map.containsKey(director_name)){
            return movie_director_map.get(director_name);
        }
        return movies;
    }

    public List<Movie> getAllMoviesAddedFromDB(){
        List<Movie> ans = new ArrayList<>();
        for(Movie m : movie_map.values()){
            ans.add(m);
        }
        return ans;
    }

    public void deleteADirectorsAllMovies(String name){
        if(movie_director_map.containsKey(name)){
            List<String> movies = movie_director_map.get(name);
            for(String str : movies){
                movie_map.remove(str);
            }
            director_map.remove(name);
        }

        if(director_map.containsKey(name)){
            director_map.remove(name);
        }
    }

    public void deleteAllDirectorsWithTheirMovies(){
        for(String str : movie_director_map.keySet()){
            List<String> temp = movie_director_map.get(str);
            for(String str1 : temp){
                if(movie_map.containsKey(str1)){
                    movie_map.remove(str1);
                }
            }
        }
    }

}
