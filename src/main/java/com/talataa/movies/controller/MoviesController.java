package com.talataa.movies.controller;

import com.talataa.movies.dto.MovieDTO;
import com.talataa.movies.dto.MoviesPageDTO;
import com.talataa.movies.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
public class MoviesController {

    @Autowired
    MovieService movieService;

    @Operation(summary = "Gets paginated movies")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MoviesPageDTO> paginatedMovies(@RequestParam(defaultValue = "1") Integer page) {
        MoviesPageDTO res = movieService.moviesByPage(page);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Get a movie by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Integer id) {
        MovieDTO res = movieService.getMovie(id);
        return ResponseEntity.ok(res);
    }

}
