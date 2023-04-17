package com.talataa.movies.services;

import com.talataa.movies.dto.MovieDTO;
import com.talataa.movies.dto.MoviesPageDTO;
import com.talataa.movies.entities.MoviesPage;
import com.talataa.movies.mapper.MovieMapper;
import com.talataa.movies.mapper.MoviesPageMapper;
import com.talataa.movies.repositories.TheMovieDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        MoviesPage moviesPage = theMovieDBRepository.findMoviesByPage(page);
        System.out.println("M: ".concat(moviesPage.toString()));
        MoviesPageDTO moviesPageDTO = moviesPageMapper.moviesPageAsMoviesPageDTO(moviesPage);
        System.out.println("MDTO: ".concat(moviesPageDTO.toString()));

        return moviesPageDTO;
    }

    public MovieDTO getMovie(Integer id) {
        return movieMapper.movieAsMovieDTO(theMovieDBRepository.findMovieById(id));
    }
}
