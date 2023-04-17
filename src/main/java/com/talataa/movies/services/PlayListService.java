package com.talataa.movies.services;

import com.talataa.movies.dto.PlayListDTO;
import com.talataa.movies.dto.PlayListProps;
import com.talataa.movies.entities.PlayList;
import com.talataa.movies.mapper.PlayListMapper;
import com.talataa.movies.repositories.TheMovieDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PlayListService {

    @Value("${movies.moviesDB.imgUrl}")
    private String IMG_URL;

    @Autowired
    PlayListMapper playListMapper;

    @Autowired
    TheMovieDBRepository theMovieDBRepository;

    private static final Integer LIMIT = 20;

    public Integer createList(String sessionId, PlayListProps props) {
        return theMovieDBRepository.createList(sessionId, props);
    }

    public PlayListDTO playListByPage(Integer listId, Integer page) {
        PlayList playList = theMovieDBRepository.getList(listId);
        Integer offset = (page - 1) * LIMIT;
        Integer totalPage = (int) Math.ceil((double) playList.getItem_count() / LIMIT);
        playList.setItems(playList.getItems().stream()
                .skip(offset)
                .limit(LIMIT)
                .collect(Collectors.toList()));
        return playListMapper.playListAsPlayListDTO(playList, page, totalPage, IMG_URL);
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
