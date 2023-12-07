package pl.szykor.movielibrary.infrastructure.movie.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szykor.movielibrary.domain.movie.MovieFacade;
import pl.szykor.movielibrary.domain.movie.dto.MovieDto;
import pl.szykor.movielibrary.domain.movie.dto.MovieRequestDto;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieRestController {

    private final MovieFacade movieFacade;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movieDtos = movieFacade.findAll();
        return ResponseEntity.ok(movieDtos);
    }

    @GetMapping("/{title}")
    public ResponseEntity<MovieDto> getMovieByTitle(@PathVariable String title) {
        MovieDto movieDto = movieFacade.findByTitle(title);
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        MovieDto movieDto = movieFacade.addMovie(movieRequestDto);
        return ResponseEntity.ok(movieDto);
    }

    @PutMapping
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieRequestDto movieRequestDto) {
        MovieDto movieDto = movieFacade.updateMovie(movieRequestDto);
        return ResponseEntity.ok(movieDto);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<MovieDto> deleteMovie(@PathVariable String title) {
        MovieDto deletedMovie = movieFacade.deleteMovie(title);
        return ResponseEntity.ok(deletedMovie);
    }
}
