package com.talataa.movies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayListDTO {

    Integer id;
    String name;
    String description;
    String createdBy;
    Integer favoriteCount;
    List<MovieDTO> movies;
    Integer page;
    Integer totalMovies;
    Integer totalPages;
    String posterUrl;

}
