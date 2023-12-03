package pl.szykor.movielibrary.domain.movie.dto;

import lombok.Builder;

@Builder
public record MovieRequestDto(String title,
                              String rating,
                              byte[] image) {
}
