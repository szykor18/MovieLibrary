package pl.szykor.movielibrary.feature;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.szykor.movielibrary.BaseIntegrationTest;
import pl.szykor.movielibrary.domain.movie.MovieFacade;
import pl.szykor.movielibrary.domain.movie.dto.MovieDto;
import pl.szykor.movielibrary.domain.movie.dto.MovieRequestDto;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserWantToAddSomeMoviesAndRateThemIntegrationTest extends BaseIntegrationTest {

    @Autowired
    MovieFacade movieFacade;

    @Test
    public void f() throws Exception {
    //step 1: user want to add some movie and rate it.
        //given && when
        ResultActions performAddMovie = mockMvc.perform(post("/movies")
                .content("""
                        {
                            "title": "Title",
                            "rating": "Rating"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcAddResult = performAddMovie.andExpect(status().isOk()).andReturn();
        String addedMovieJson = mvcAddResult.getResponse().getContentAsString();
        MovieDto addedMovie = objectMapper.readValue(addedMovieJson, MovieDto.class);
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
        //given && when
        ResultActions performUpdateMovie = mockMvc.perform(put("/movies")
                .content("""
                        {
                            "title": "Title",
                            "rating": "Rating+1"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcUpdateResult = performUpdateMovie.andExpect(status().isOk()).andReturn();
        String updatedMovieJson = mvcUpdateResult.getResponse().getContentAsString();
        MovieDto updatedMovie = objectMapper.readValue(updatedMovieJson, MovieDto.class);
        List<MovieDto> moviesDtos2 = movieFacade.findAll();
        //then
        assertThat(moviesDtos2).containsExactlyInAnyOrder(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating+1")
                .build());


    //step 3: User want see to all movies and system returns movies: [movieDto: id:1, Title:Title, Rating:Rating+1]
        //given && when
        List<MovieDto> movies = movieFacade.findAll();
        //then
        assertThat(movies).containsExactlyInAnyOrder(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating+1")
                .build());
        assertThat(movies).hasSize(1);


    //step 3: User want to see movie by title: "Title" and system returns one movie (id:1, Title:Title, Rating:Rating+1)
        //given && when
        MovieDto movieDto = movieFacade.findByTitle("Title");
        //then
        assertThat(movieDto).isEqualTo(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating+1")
                .build());


    //step 4: User want to delete movie by title: Title
        //given
        MovieDto deletedMovie = movieFacade.deleteMovie("Title");
        //when
        List<MovieDto> moviesDtos3 = movieFacade.findAll();
        //then
        assertThat(moviesDtos3).hasSize(0);
    }
}
