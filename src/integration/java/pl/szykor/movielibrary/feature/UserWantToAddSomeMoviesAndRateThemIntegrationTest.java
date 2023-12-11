package pl.szykor.movielibrary.feature;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.szykor.movielibrary.BaseIntegrationTest;
import pl.szykor.movielibrary.domain.movie.MovieFacade;
import pl.szykor.movielibrary.domain.movie.dto.MovieDto;
import pl.szykor.movielibrary.infrastructure.loginandregister.controller.dto.LoginResultDto;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserWantToAddSomeMoviesAndRateThemIntegrationTest extends BaseIntegrationTest {

    @Autowired
    MovieFacade movieFacade;

    @Test
    public void f() throws Exception {

    //step 0: user register , login and get token
        //given && wheb
        ResultActions performRegister = mockMvc.perform(post("/register")
                .content("""
                        {
                        "username": "user",
                        "password": "pass"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON));
        performRegister.andExpect(status().isCreated());
        //then
        ResultActions performLogin = mockMvc.perform(post("/login")
                .content("""
                        {
                        "username": "user",
                        "password": "pass"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcResult = performLogin.andExpect(status().isOk()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        LoginResultDto loginResultDto = objectMapper.readValue(json, LoginResultDto.class);
        String token = loginResultDto.token();


        //step 1: user want to add some movie and rate it.
        //given && when
        ResultActions performAddMovie = mockMvc.perform(post("/movies")
                .header("Authorization", "Bearer " + token)
                .content("""
                        {
                            "title": "Title",
                            "rating": "Rating"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcAddResult = performAddMovie.andExpect(status().isCreated()).andReturn();
        String addedMovieJson = mvcAddResult.getResponse().getContentAsString();
        MovieDto addedMovie = objectMapper.readValue(addedMovieJson, MovieDto.class);
        Integer id = addedMovie.id();
        //then
        assertThat(addedMovie).isEqualTo(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating")
                .build());


    //step 2: User made a mistake and want to update previous movie he added.
        //given && when
        ResultActions performUpdateMovie = mockMvc.perform(put("/movies")
                .header("Authorization", "Bearer " + token)
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
        //then
        assertThat(updatedMovie).isEqualTo(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating+1")
                .build());


    //step 3: User want see to all movies and system returns movies: [movieDto: id:1, Title:Title, Rating:Rating+1]
        //given && when
        ResultActions performGetAll = mockMvc.perform(get("/movies")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcGetAllResult = performGetAll.andExpect(status().isOk()).andReturn();
        String getAllMoviesJson = mvcGetAllResult.getResponse().getContentAsString();
        List<MovieDto> allMovies = objectMapper.readValue(getAllMoviesJson, new TypeReference<>(){});
        //then
        assertThat(allMovies).containsExactlyInAnyOrder(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating+1")
                .build());
        assertThat(allMovies).hasSize(1);


    //step 4: User want to see movie by title: "Title" and system returns movie (id:1, Title:Title, Rating:Rating+1)
        //given && when
        ResultActions performGetByTitle = mockMvc.perform(get("/movies/Title")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcGetByTitleAllResult = performGetByTitle.andExpect(status().isOk()).andReturn();
        String getMovieByTitleJson = mvcGetByTitleAllResult.getResponse().getContentAsString();
        MovieDto movieByTitle = objectMapper.readValue(getMovieByTitleJson, MovieDto.class);
        //then
        assertThat(movieByTitle).isEqualTo(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating+1")
                .build());


    //step 5: User want to delete movie by title: Title
        //given && when
        ResultActions performDeleteMovie = mockMvc.perform(delete("/movies/Title")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcDeleteResult = performDeleteMovie.andExpect(status().isOk()).andReturn();
        String deletedMovieJson = mvcDeleteResult.getResponse().getContentAsString();
        MovieDto deletedMovie = objectMapper.readValue(deletedMovieJson, MovieDto.class);
        //then
        assertThat(deletedMovie).isEqualTo(MovieDto.builder()
                .id(id)
                .title("Title")
                .rating("Rating+1")
                .build());

        //step 6: User want to see movie by title: "Title" and system returns status 404 NOT_FOUND, because user deleted it before
        //given && when
        ResultActions performGetByNotExistingTitle = mockMvc.perform(get("/movies/Title")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON));
        //then
        performGetByNotExistingTitle.andExpect(status().isNotFound()).andExpect(
                content().json("""
                               {
                               "message": "Movie with title Title not found",
                               "status": "NOT_FOUND"
                               }
                               """.trim())
        );
    }


}
