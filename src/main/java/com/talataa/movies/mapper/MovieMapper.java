package com.talataa.movies.mapper;


import com.talataa.movies.dto.MovieDTO;
import com.talataa.movies.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Named("movieMapper")
@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target="hasVideo", source="video")
    @Mapping(target="hasAdultContent", source="adult")
    @Mapping(target="releaseDate", source="release_date", dateFormat = "yyyy-MM-dd")
    @Mapping(target="originalLanguage", source="original_language")
    @Mapping(target="minutesOfDuration", source="runtime")
    @Mapping(target="posterUrl", expression = "java((src.getPoster_path() != null) ? \"https://image.tmdb.org/t/p/original\".concat(src.getPoster_path()) : null)")
    @Mapping(target="rating", source="vote_average")
    @Mapping(target="ratingCount ", source="vote_count")
    @Mapping(target="genreIds ", source="genre_ids")
    public MovieDTO movieAsMovieDTO (Movie src);

    public List<MovieDTO> moviesAsMovieDTOs (List<Movie> src);

}
