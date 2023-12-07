package pl.szykor.movielibrary.domain.movie.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MovieRequestDto(
        @NotEmpty(message = "{title.not.empty}")
        @NotNull(message = "{title.not.null}")
        String title,
        @NotEmpty(message = "{rating.not.empty}")
        @NotNull(message = "{rating.not.null}")
        String rating,
        byte[] image) {
}
