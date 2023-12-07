package pl.szykor.movielibrary.domain.movie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MovieFacadeConfiguration {

    @Bean
    public MovieFacade movieFacade(MovieRepository movieRepository) {
        MovieService movieService = new MovieService(movieRepository);
        return new MovieFacade(movieService);
    }
}
