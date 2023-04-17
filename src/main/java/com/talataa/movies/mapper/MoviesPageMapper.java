package com.talataa.movies.mapper;


import com.talataa.movies.dto.MoviesPageDTO;
import com.talataa.movies.entities.MoviesPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MovieMapper.class)
public interface MoviesPageMapper {

    @Mapping(target="totalMovies", source="total_results")
    @Mapping(target="totalPages", source="total_pages")
    @Mapping(target="movies", source="results", qualifiedByName = "movieMapper")
    public MoviesPageDTO moviesPageAsMoviesPageDTO (MoviesPage src);

}
