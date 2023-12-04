package pl.szykor.movielibrary.domain.movie;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import pl.szykor.movielibrary.domain.movie.dto.MovieDto;
import pl.szykor.movielibrary.domain.movie.dto.MovieRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MovieFacadeTest {

    private final MovieFacade movieFacade = new MovieFacade(new MovieService(new InMemoryMovieRepositoryTestImpl()));
    @Test
    public void should_find_movie_by_title_and_return_correct_movie() {
        //given
        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .title("Title")
                .rating("Rating")
                .build();
        //when
        movieFacade.addMovie(movieRequestDto);
        MovieDto movieDto = movieFacade.findByTitle("Title");
        //then
        assertAll(
                () -> assertThat(movieDto.id()).isNotNull(),
                () -> assertThat(movieDto.title()).isEqualTo("Title"),
                () -> assertThat(movieDto.rating()).isEqualTo("Rating")
        );
    }
    @Test
    public void should_find_all_movies_and_return_correct_movies() {
        //given
        MovieRequestDto movieRequestDto1 = MovieRequestDto.builder()
                .title("Title1")
                .rating("Rating1")
                .build();
        MovieRequestDto movieRequestDto2 = MovieRequestDto.builder()
                .title("Title2")
                .rating("Rating2")
                .build();
        //when
        movieFacade.addMovie(movieRequestDto1);
        movieFacade.addMovie(movieRequestDto2);
        List<MovieDto> movieDtos = movieFacade.findAll();
        String id1 = movieDtos.get(0).id();
        String id2 = movieDtos.get(1).id();
        //then
        assertThat(movieDtos).containsExactlyInAnyOrder(
                MovieDto.builder()
                        .id(id1)
                        .title("Title1")
                        .rating("Rating1")
                        .build(),
                MovieDto.builder()
                        .id(id2)
                        .title("Title2")
                        .rating("Rating2")
                        .build()
        );
    }
    @Test
    public void should_throw_an_exception_when_find_by_title_not_found_movie() {
        //given
        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .title("Title")
                .rating("Rating")
                .build();
        //when
        Throwable throwable = catchThrowable(() -> movieFacade.findByTitle("Title"));
        //then
        AssertionsForClassTypes.assertThat(throwable)
                .isInstanceOf(MovieNotFoundException.class)
                .hasMessage("Movie with title Title not found");
    }
    @Test
    public void should_add_movie_to_database_and_return_that_movie() {
        //given
        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .title("Title")
                .rating("Rating")
                .build();
        //when
        MovieDto addedMovie = movieFacade.addMovie(movieRequestDto);
        List<MovieDto> database = movieFacade.findAll();
        //then
        assertAll(
                () -> assertThat(addedMovie.id()).isNotNull(),
                () -> assertThat(addedMovie.title()).isEqualTo("Title"),
                () -> assertThat(addedMovie.rating()).isEqualTo("Rating"),
                () -> assertThat(database).contains(addedMovie)
        );
    }
    @Test
    public void should_update_movie_from_database_and_return_that_movie() {
        //given
        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .title("Title")
                .rating("Rating")
                .build();
        MovieRequestDto movieToUpdate = MovieRequestDto.builder()
                .title("Title")
                .rating("Updated Rating")
                .build();
        //when
        movieFacade.addMovie(movieRequestDto);
        MovieDto movieDto = movieFacade.updateMovie(movieToUpdate);
        List<MovieDto> moviesDtos = movieFacade.findAll();
        //then
        assertAll(
                () -> assertThat(movieDto.id()).isNotNull(),
                () -> assertThat(movieDto.title()).isEqualTo("Title"),
                () -> assertThat(movieDto.rating()).isEqualTo("Updated Rating"),
                () -> assertThat(moviesDtos).contains(movieDto)
        );
    }
    @Test
    public void should_delete_movie_from_database_and_return_that_movie() {
        //given
        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .title("Title")
                .rating("Rating")
                .build();
        //when
        movieFacade.addMovie(movieRequestDto);
        MovieDto movieDto = movieFacade.deleteMovie("Title");
        List<MovieDto> moviesDtos = movieFacade.findAll();
        //then
        assertAll(
                () -> assertThat(movieDto.id()).isNotNull(),
                () -> assertThat(movieDto.title()).isEqualTo("Title"),
                () -> assertThat(movieDto.rating()).isEqualTo("Rating"),
                () -> assertThat(moviesDtos).doesNotContain(movieDto)
        );
    }

}
