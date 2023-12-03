package pl.szykor.movielibrary.domain.movie.dto;

import lombok.Builder;

@Builder
public record MovieDto(String id,
                       String title,
                       String rating,
                       byte[] image) {
}
