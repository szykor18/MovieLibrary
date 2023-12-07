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
        assertThat(moviesDtos).hasSize(1);


    //step 2: User made a mistake and want to update previous movie he added.
        //given
        MovieRequestDto updateMovieRequest = MovieRequestDto.builder()
                .title("Title")
                .rating("Rating+1")
                .build();
        //when
        MovieDto updatedMovie = movieFacade.updateMovie(updateMovieRequest);
        List<MovieDto> moviesDtos2 = movieFacade.findAll();
        //then
        assertThat(moviesDtos2).containsExactlyInAnyOrder(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating+1")
                .build());


    //step 3: User want to all movies and system returns one movie (id:1, Title:Title, Rating:Rating+1)
        //given && when
        List<MovieDto> movies = movieFacade.findAll();
        //then
        assertThat(movies).containsExactlyInAnyOrder(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating+1")
                .build());
        assertThat(movies).hasSize(1);

    //step 4: User want to delete movie by title: Title
        //given
        MovieRequestDto getMovieRequest = MovieRequestDto.builder()
                .title("Title")
                .rating("Rating+1")
                .build();
        //when
        MovieDto deletedMovie = movieFacade.deleteMovie("Title");
        List<MovieDto> moviesDtos3 = movieFacade.findAll();
        //then
        assertThat(moviesDtos3).hasSize(0);
    }
}
