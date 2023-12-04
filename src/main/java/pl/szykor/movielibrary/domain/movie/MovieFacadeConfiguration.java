package pl.szykor.movielibrary.domain.movie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieFacadeConfiguration {

    @Bean
    public MovieFacade movieFacade(MovieRepository movieRepository) {
        MovieService movieService = new MovieService(movieRepository);
        return new MovieFacade(movieService);
    }
}
