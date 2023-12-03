package pl.szykor.movielibrary.domain.movie;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryMovieRepositoryTestImpl implements MovieRepository {
    private final Map<String, Movie> movies = new ConcurrentHashMap<>();


    @Override
    public Optional<Movie> findByTitle(String title) {
        return Optional.ofNullable(movies.get(title));
    }

    @Override
    public List<Movie> findAll() {
        return movies.values().stream().collect(Collectors.toList());
    }

    @Override
    public Movie add(Movie entity) {
        String id = UUID.randomUUID().toString();
        Movie movie = Movie.builder()
                .id(id)
                .title(entity.title())
                .rating(entity.rating())
                .image(entity.image())
                .build();
        movies.put(movie.title(), movie);
        return movie;
    }

    @Override
    public Movie update(Movie entity) {
        Movie movie = movies.get(entity.title());
        String id = movie.id();
        Movie savedMovie = Movie.builder()
                .id(id)
                .title(entity.title())
                .rating(entity.rating())
                .image(entity.image())
                .build();
        movies.put(savedMovie.title(), savedMovie);
        return savedMovie;
    }

    @Override
    public Movie delete(Movie movie) {
        return movies.remove(movie.title());
    }
}