package com.szodi.workflex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szodi.workflex.dto.MovieDto;
import com.szodi.workflex.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
@AutoConfigureMockMvc(addFilters = false)
class MovieControllerWebMvcTest {

    @Autowired MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    MovieService movieService;

    @Test
    void getMovieList_returns200AndJsonArray() throws Exception {
        MovieDto m1 = MovieDto.builder().id(1L).title("Inception").genre("Sci-Fi").imdbRating("8.8").build();
        MovieDto m2 = MovieDto.builder().id(2L).title("Heat").genre("Crime").imdbRating("8.2").build();

        when(movieService.getMovieList()).thenReturn(List.of(m1, m2));

        mockMvc.perform(get("/api/movies/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Inception"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].genre").value("Crime"));

        verify(movieService).getMovieList();
        verifyNoMoreInteractions(movieService);
    }

    @Test
    void createMovie_postsBody_returns200AndCreatedDto() throws Exception {
        MovieDto request = MovieDto.builder().title("Interstellar").genre("Sci-Fi").imdbRating("8.6").build();
        MovieDto response = MovieDto.builder().id(10L).title("Interstellar").genre("Sci-Fi").imdbRating("8.6").build();

        when(movieService.createMovie(ArgumentMatchers.any(MovieDto.class))).thenReturn(response);

        mockMvc.perform(post("/api/movies/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.title").value("Interstellar"))
                .andExpect(jsonPath("$.imdbRating").value("8.6"));

        verify(movieService).createMovie(ArgumentMatchers.any(MovieDto.class));
        verifyNoMoreInteractions(movieService);
    }

    @Test
    void updateMovie_putsBody_returns200AndUpdatedDto() throws Exception {
        MovieDto request = MovieDto.builder().id(5L).title("Heat").genre("Crime").imdbRating("8.2").build();
        MovieDto response = MovieDto.builder().id(5L).title("Heat").genre("Crime").imdbRating("8.2").build();

        when(movieService.updateMovie(ArgumentMatchers.any(MovieDto.class))).thenReturn(response);

        mockMvc.perform(put("/api/movies/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.title").value("Heat"));

        verify(movieService).updateMovie(ArgumentMatchers.any(MovieDto.class));
        verifyNoMoreInteractions(movieService);
    }

    @Test
    void getMovieDetails_returns200AndMovie() throws Exception {
        MovieDto response = MovieDto.builder().id(123L).title("Alien").genre("Horror").imdbRating("8.5").build();
        when(movieService.getMovieDetails(123L)).thenReturn(response);

        mockMvc.perform(get("/api/movies/{movieId}", 123L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(123))
                .andExpect(jsonPath("$.title").value("Alien"))
                .andExpect(jsonPath("$.genre").value("Horror"));

        verify(movieService).getMovieDetails(123L);
        verifyNoMoreInteractions(movieService);
    }

    @Test
    void deleteMovie_returns200AndDelegates() throws Exception {
        mockMvc.perform(delete("/api/movies/{movieId}", 123L))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(movieService).deleteMovie(123L);
        verifyNoMoreInteractions(movieService);
    }
}
