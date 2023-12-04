package pl.szykor.movielibrary.domain.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "rating")
    private String rating;
    @Column(name = "image")
    private byte[] image;
}
