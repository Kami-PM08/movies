package com.talataa.movies.services;

import com.talataa.movies.dto.MoviesListDTO;
import com.talataa.movies.entities.CreateMoviesListProps;
import com.talataa.movies.entities.MoviesList;
import com.talataa.movies.mapper.MoviesListMapper;
import com.talataa.movies.repositories.TheMovieDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MoviesListService {

    @Autowired
    MoviesListMapper moviesListMapper;

    @Autowired
    TheMovieDBRepository theMovieDBRepository;

    private static final Integer LIMIT = 20;

    public Integer createList(String sessionId, CreateMoviesListProps props) {
        return theMovieDBRepository.createList(sessionId, props);
    }

    public MoviesListDTO moviesListByPage(Integer listId, Integer page) {
        MoviesList moviesList = theMovieDBRepository.getList(listId);
        Integer offset = (page - 1) * LIMIT;
        moviesList.setItems(moviesList.getItems().stream()
                .skip(offset)
                .limit(LIMIT)
                .collect(Collectors.toList()));
        return moviesListMapper.moviesListAsMoviesListDTO(moviesList, page, LIMIT);
    }

    public void addMovieToList(String sessionId, Integer listId, Integer movieId) {
        theMovieDBRepository.addMovieToList(sessionId, listId, movieId);
    }

    public void removeMovieToList(String sessionId, Integer listId, Integer movieId) {
        theMovieDBRepository.removeMovieToList(sessionId, listId, movieId);
    }

    public void clearMoviesList(String sessionId, Integer listId) {
        theMovieDBRepository.clearList(sessionId, listId);
    }

    public void deleteMoviesList(String sessionId, Integer listId) {
        theMovieDBRepository.deleteList(sessionId, listId);
    }

}
