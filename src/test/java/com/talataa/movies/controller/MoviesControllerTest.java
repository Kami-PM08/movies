package com.talataa.movies.controller;

import com.talataa.movies.dto.MovieDTO;
import com.talataa.movies.services.MovieService;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest(MoviesController.class)
public class MoviesControllerTest {
    @MockBean
    private MovieService movieService;

    @Autowired
    private MoviesController moviesController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void getMovie_when_data_is_ok_expect_complete_object() {
        MovieDTO expected = MovieDTO.builder().title("").id(99).hasVideo(true).status("").tagline("").minutesOfDuration(0).releaseDate(new Date()).build();
        when(movieService.getMovie(99)).thenReturn(expected);
        ResponseEntity<MovieDTO> result = moviesController.getMovie(99);
        assertEquals(result, ResponseEntity.ok(expected));
    }

}
