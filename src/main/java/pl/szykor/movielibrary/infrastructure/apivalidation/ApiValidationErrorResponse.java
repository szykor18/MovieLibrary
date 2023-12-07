package pl.szykor.movielibrary.infrastructure.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiValidationErrorResponse(List<String> messages, HttpStatus status) {
}
