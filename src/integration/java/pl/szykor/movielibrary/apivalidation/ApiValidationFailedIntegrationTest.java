package pl.szykor.movielibrary.apivalidation;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.szykor.movielibrary.BaseIntegrationTest;
import pl.szykor.movielibrary.infrastructure.apivalidation.ApiValidationErrorResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiValidationFailedIntegrationTest extends BaseIntegrationTest {

    @WithMockUser
    @Test
    public void should_return_400_bad_request_status_when_title_is_null_and_empty() throws Exception {
        //given && when
        ResultActions perform = mockMvc.perform(post("/movies")
                .content("""
                        {
                        "rating": "rating"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorResponse apiValidationErrorResponse = objectMapper.readValue(json, ApiValidationErrorResponse.class);
        List<String> messages = apiValidationErrorResponse.messages();
        assertThat(messages).hasSize(2).containsExactlyInAnyOrder("title must not be null",
                "title must not be empty");
    }

    @WithMockUser
    @Test
    public void should_return_400_bad_request_status_when_rating_is_empty() throws Exception {
        //given && when
        ResultActions perform = mockMvc.perform(post("/movies")
                .content("""
                        {
                        "title": "Title",
                        "rating": ""
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorResponse apiValidationErrorResponse = objectMapper.readValue(json, ApiValidationErrorResponse.class);
        assertThat(apiValidationErrorResponse.messages()).hasSize(1)
                .containsExactlyInAnyOrder("rating must not be empty");
    }
}
