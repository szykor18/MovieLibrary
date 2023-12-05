package pl.szykor.movielibrary.domain.movie;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryMovieRepositoryTestImpl implements MovieRepository {
    private final Map<String, Movie> movies = new ConcurrentHashMap<>();


    @Override
    public Optional<Movie> findByTitle(String title) {
        return Optional.ofNullable(movies.get(title));
    }

    @Override
    public Movie deleteByTitle(String title) {
        Movie movie = movies.get(title);
        movies.remove(title);
        return movie;
    }

    @Override
    public <S extends Movie> S save(S entity) {
        Integer id = new Random().nextInt(10000);
        Movie movie = Movie.builder()
                .id(id)
                .title(entity.getTitle())
                .rating(entity.getRating())
                .image(entity.getImage())
                .build();
        movies.put(movie.getTitle(), movie);
        return (S) movie;
    }

    @Override
    public <S extends Movie> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Movie> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Movie> findAll() {
        return movies.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Movie> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Movie entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Movie> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Movie> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Movie> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Movie> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Movie getOne(Integer integer) {
        return null;
    }

    @Override
    public Movie getById(Integer integer) {
        return null;
    }

    @Override
    public Movie getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Movie> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Movie> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Movie> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Movie> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Movie> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Movie> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Movie, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Movie> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        return null;
    }
}