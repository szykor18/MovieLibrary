package pl.szykor.movielibrary.domain.loginandregister;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}

