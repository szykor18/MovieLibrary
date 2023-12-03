package pl.szykor.movielibrary.domain.movie;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String title) {
        super("Movie with title " + title + " not found");
    }
}
