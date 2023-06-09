package com.talataa.movies.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoviesPageDTO {
    Integer page;
    List<MovieDTO> movies;
    Integer totalMovies;
    Integer totalPages;
}
