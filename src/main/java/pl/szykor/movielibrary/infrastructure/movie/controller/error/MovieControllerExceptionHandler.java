package pl.szykor.movielibrary.infrastructure.movie.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.szykor.movielibrary.domain.movie.MovieNotFoundException;

@ControllerAdvice
public class MovieControllerExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MovieErrorResponseDto handleMovieNotFoundException(MovieNotFoundException movieNotFoundException) {
        String message = movieNotFoundException.getMessage();
        return new MovieErrorResponseDto(message, HttpStatus.NOT_FOUND);
    }
}
