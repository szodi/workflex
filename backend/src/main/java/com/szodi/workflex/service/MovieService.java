package com.szodi.workflex.service;

import com.szodi.workflex.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovieList();
    MovieDto createMovie(MovieDto movieDto);
    MovieDto getMovieDetails(Long movieId);
    MovieDto updateMovie(MovieDto movieDto);
    void deleteMovie(Long movieId);
}
