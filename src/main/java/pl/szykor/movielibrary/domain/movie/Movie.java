package pl.szykor.movielibrary.domain.movie;

import lombok.Builder;

@Builder
record Movie (String id,
        String title,
        String rating,
         byte[] image) {

}
