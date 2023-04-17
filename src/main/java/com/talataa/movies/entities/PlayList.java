package com.talataa.movies.entities;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayList {

    String id;
    String name;
    String description;
    String created_by;
    Integer favorite_count;
    Integer item_count;
    List<Movie> items;
    String poster_path;

}
