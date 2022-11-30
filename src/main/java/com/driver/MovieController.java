package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody(required = true)Movie movie){
        movieService.addMovieService(movie);
        return new ResponseEntity("User successfully created " , HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody(required = true)Director director){
        movieService.addDirectorService(director);
        return new ResponseEntity("User successfully created" , HttpStatus.CREATED);
    }


    @PostMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie_name,
                                               @RequestParam String director_name){
        movieService.addMovieDirectorPairService(movie_name , director_name);
        return new ResponseEntity("Pair added" , HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movie_name){
        Movie movie = movieService.getMovieByMovieNameService(movie_name);
        return new ResponseEntity<>(movie , HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String director_name){
        Director director = movieService.getDirectorByDirectorNameService(director_name);
        return new ResponseEntity<>(director , HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public  ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String director_name){
        List<String> list = movieService.getListOfMoviesByDirectorNameService(director_name);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies(){
        List<Movie> list = movieService.getListOfAllMoviesService();
        return new ResponseEntity(list, HttpStatus.CREATED);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name")String director_name){
        movieService.deleteADirectorWithAllHisMoviesService(director_name);
        return new ResponseEntity(director_name+ " deleted successfully" , HttpStatus.CREATED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectorsService();
        return new ResponseEntity("Directors deleted successfully" , HttpStatus.CREATED);
    }



}
