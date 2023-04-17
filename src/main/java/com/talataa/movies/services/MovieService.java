package com.talataa.movies.services;

import com.talataa.movies.dto.MovieDTO;
import com.talataa.movies.dto.MoviesPageDTO;
import com.talataa.movies.mapper.MovieMapper;
import com.talataa.movies.mapper.MoviesPageMapper;
import com.talataa.movies.repositories.TheMovieDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MoviesPageMapper moviesPageMapper;

    @Autowired
    TheMovieDBRepository theMovieDBRepository;

    public MoviesPageDTO moviesByPage(Integer page) {
        return moviesPageMapper.moviesPageAsMoviesPageDTO(theMovieDBRepository.findMoviesByPage(page));
    }

    public MovieDTO getMovie(Integer id) {
        return movieMapper.movieAsMovieDTO(theMovieDBRepository.findMovieById(id));
    }
}
