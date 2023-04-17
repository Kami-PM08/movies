package com.talataa.movies.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    Integer id;
    String title;
    String overview;
    Boolean adult;
    String release_date;
    String original_language;
    Boolean video;
    String poster_path;
    Float vote_average;
    Integer vote_count;
    List<Integer> genre_ids;
    List<Genre> genres;

    String status;
    String tagline;
    Integer runtime;

}
