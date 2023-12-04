package pl.szykor.movielibrary.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByTitle(String title);

    Movie deleteByTitle(String title);

}
