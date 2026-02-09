package com.szodi.workflex.mapper;

import com.szodi.workflex.dto.MovieDto;
import com.szodi.workflex.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieDto convert(Movie movie) {
        if (movie == null) {
            return null;
        }
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .imdbRating(movie.getImdbRating())
                .build();
    }
}
