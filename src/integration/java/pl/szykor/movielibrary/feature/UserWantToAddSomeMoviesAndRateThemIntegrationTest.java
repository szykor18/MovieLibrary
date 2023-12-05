package pl.szykor.movielibrary.feature;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szykor.movielibrary.BaseIntegrationTest;
import pl.szykor.movielibrary.domain.movie.MovieFacade;
import pl.szykor.movielibrary.domain.movie.dto.MovieDto;
import pl.szykor.movielibrary.domain.movie.dto.MovieRequestDto;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UserWantToAddSomeMoviesAndRateThemIntegrationTest extends BaseIntegrationTest {

    @Autowired
    MovieFacade movieFacade;

    @Test
    public void f() {
    //step 1: user want to add some movie and rate it.
        //given
        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                        .title("Title")
                                .rating("Rating")
                                        .build();
        //when
        MovieDto addedMovie = movieFacade.addMovie(movieRequestDto);
        Integer id = addedMovie.id();
        List<MovieDto> moviesDtos = movieFacade.findAll();
        //then
        assertThat(moviesDtos).containsExactlyInAnyOrder(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating")
                .build());
    //step 2
    //step 3
    }
}
