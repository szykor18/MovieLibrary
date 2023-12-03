package pl.szykor.movielibrary.domain.movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    Optional<Movie> findByTitle(String title);
    List<Movie> findAll();
    Movie add(Movie movie);
    Movie update(Movie movie);
    Movie delete(Movie movie);
}
