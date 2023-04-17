package com.talataa.movies.mapper;


import com.talataa.movies.dto.MoviesListDTO;
import com.talataa.movies.entities.MoviesList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MovieMapper.class)
public interface MoviesListMapper {

    @Mapping(target = "createdBy", source = "src.created_by")
    @Mapping(target = "favoriteCount", source = "src.favorite_count")
    @Mapping(target = "totalMovies", source = "src.item_count")
    @Mapping(target = "page", source = "page")
    @Mapping(target = "movies", source = "src.items", qualifiedByName = "movieMapper")
    @Mapping(target = "totalPages", expression = "java((int) Math.ceil((double) src.getItem_count() / limit))")
    @Mapping(target = "posterUrl", expression = "java((src.getPoster_path() != null) ? \"https://image.tmdb.org/t/p/original\".concat(src.getPoster_path()) : null)")
    public MoviesListDTO moviesListAsMoviesListDTO(MoviesList src, Integer page, Integer limit);

}
