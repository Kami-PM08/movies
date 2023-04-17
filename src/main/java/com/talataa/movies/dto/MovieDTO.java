package com.talataa.movies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {
    Integer id;
    String title;
    String overview;
    Boolean hasVideo;
    Date releaseDate;
    Boolean hasAdultContent;
    String originalLanguage;
    String posterUrl;
    Float rating;
    Integer ratingCount;
    List<Integer> genreIds;
    List<GenreDTO> genres;

    String status;
    String tagline;
    Integer minutesOfDuration;
}
