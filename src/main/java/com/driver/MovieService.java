package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;


    public void addMovieService(Movie movie){
        movieRepository.addMovieToDB(movie);
    }

    public void addDirectorService(Director director){
        movieRepository.addDirectorToDB(director);
    }

    public void addMovieDirectorPairService(String movie_name , String director_name){
        movieRepository.addMovieDirectorPair(movie_name , director_name);
    }

    public Movie getMovieByMovieNameService(String movie_name){
        return movieRepository.getMovieByMovieNameFromDB(movie_name);
    }

    public Director getDirectorByDirectorNameService(String director_name){
        return movieRepository.getDirectorByDirectorNameFromDB(director_name);
    }

    public List<String> getListOfMoviesByDirectorNameService(String director_name){
        return movieRepository.getListOfMoviesOfADirector(director_name);
    }

    public List<Movie> getListOfAllMoviesService(){
        return movieRepository.getAllMoviesAddedFromDB();
    }

    public void deleteADirectorWithAllHisMoviesService(String director_name){
        movieRepository.deleteADirectorsAllMovies(director_name);
    }

    public void deleteAllDirectorsService(){
        movieRepository.deleteAllDirectorsWithTheirMovies();
    }

}
