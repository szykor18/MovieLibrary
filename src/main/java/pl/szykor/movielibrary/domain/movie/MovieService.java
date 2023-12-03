package pl.szykor.movielibrary.domain.movie;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
class MovieService {
    private final MovieRepository movieRepository;

    Movie findByTitle(String title) {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundException(title));
    }

    List<Movie> findAll() {
        return movieRepository.findAll();
    }

    Movie addMovie(Movie movie) {
        Movie addedMovie = movieRepository.add(movie);
        return addedMovie;
    }
    Movie updateMovie(Movie movie) {
        Movie updatedMovie = movieRepository.update(movie);
        return updatedMovie;
    }

    Movie deleteMovie(Movie movie) {
        Movie deletedMovie = movieRepository.delete(movie);
        return deletedMovie;
    }
}
