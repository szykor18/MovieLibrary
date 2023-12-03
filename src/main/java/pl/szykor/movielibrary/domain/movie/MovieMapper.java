package pl.szykor.movielibrary.domain.movie;

import pl.szykor.movielibrary.domain.movie.dto.MovieDto;
import pl.szykor.movielibrary.domain.movie.dto.MovieRequestDto;

class MovieMapper {
    static Movie mapFromMovieRequestDtoToMovie(MovieRequestDto movieRequestDto) {
        return Movie.builder()
                .title(movieRequestDto.title())
                .rating(movieRequestDto.rating())
                .image(movieRequestDto.image())
                .build();
    }
    static MovieDto mapFromMovieToDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.id())
                .title(movie.title())
                .rating(movie.rating())
                .image(movie.image())
                .build();
    }
}
