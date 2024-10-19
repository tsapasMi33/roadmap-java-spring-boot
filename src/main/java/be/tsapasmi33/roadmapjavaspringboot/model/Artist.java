package be.tsapasmi33.roadmapjavaspringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", length = 60, nullable = false)
    @NotBlank(message = "The firstname must not be empty.")
    @Size(min=2, max=60, message = "The firstname must be between 2 and 60 characters long.")
    private String firstname;

    @Column(name = "lastname", length = 60, nullable = false)
    @NotBlank(message = "The lastname must not be empty.")
    @Size(min=2, max=60, message = "The firstname must be between 2 and 60 characters long.")

    private String lastname;

    public Artist(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return firstname + ' ' + lastname;
    }
}
