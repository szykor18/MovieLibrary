package pl.szykor.movielibrary.domain.movie;

import lombok.AllArgsConstructor;

import java.util.List;

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
        Movie addedMovie = movieRepository.save(movie);
        return addedMovie;
    }
    Movie updateMovie(Movie movie) {
        Movie updatedMovie = movieRepository.save(movie);
        return updatedMovie;
    }

    Movie deleteByTitle(String title) {
        Movie deletedMovie = movieRepository.deleteByTitle(title);
        return deletedMovie;
    }
}
