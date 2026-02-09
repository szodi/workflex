package com.szodi.workflex.service;

import com.szodi.workflex.dto.MovieDto;
import com.szodi.workflex.exception.MovieNotFoundException;
import com.szodi.workflex.mapper.MovieMapper;
import com.szodi.workflex.model.Movie;
import com.szodi.workflex.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public List<MovieDto> getMovieList() {
        return movieRepository.findAll().stream().map(movieMapper::convert).toList();
    }

    @Override
    @Transactional
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = Movie.builder()
                .title(movieDto.getTitle())
                .genre(movieDto.getGenre())
                .imdbRating(movieDto.getImdbRating())
                .build();
        movieRepository.save(movie);
        return movieMapper.convert(movie);
    }

    @Override
    public MovieDto getMovieDetails(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        return movieMapper.convert(movie);
    }

    @Override
    @Transactional
    public MovieDto updateMovie(MovieDto movieDto) {
        if (movieDto.getId() == null) {
            throw new MovieNotFoundException();
        }
        Movie movie = movieRepository.findById(movieDto.getId()).orElseThrow(MovieNotFoundException::new);

        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setImdbRating(movieDto.getImdbRating());

        movieRepository.save(movie);;
        return movieMapper.convert(movie);
    }

    @Override
    @Transactional
    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        movieRepository.delete(movie);
    }
}
