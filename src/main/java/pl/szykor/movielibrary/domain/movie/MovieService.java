package pl.szykor.movielibrary.domain.movie;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
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
        return movieRepository.save(movie);
    }
    Movie updateMovie(Movie movie) {
        Optional<Movie> movieByTitle = movieRepository.findByTitle(movie.getTitle());
        if (movieByTitle.isPresent()) {
            int id = movieByTitle.get().getId();
            return movieRepository.save(new Movie(id, movie.getTitle(), movie.getRating(), movie.getImage()));
        }
        throw new MovieNotFoundException(movie.getTitle());
    }
    @Transactional
    public Movie deleteByTitle(String title) {
        Movie movieByTitle = movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundException(title));
        movieRepository.delete(movieByTitle);
        return movieByTitle;
    }
}
