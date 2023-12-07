package pl.szykor.movielibrary.infrastructure.movie.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szykor.movielibrary.domain.movie.MovieFacade;
import pl.szykor.movielibrary.domain.movie.dto.MovieDto;
import pl.szykor.movielibrary.domain.movie.dto.MovieRequestDto;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieRestController {

    private final MovieFacade movieFacade;

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
}
