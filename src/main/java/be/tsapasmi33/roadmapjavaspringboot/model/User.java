package be.tsapasmi33.roadmapjavaspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", length = 60, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", length = 60, nullable = false)
    private String firstname;

    @Column(name = "lastname", length = 60, nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    @Setter
    @Column(name = "language", length = 2, nullable = false)
    private String language;

    @Setter
    @Column(name = "role", nullable = false, columnDefinition = "enum('admin','member')")
    private String role;

    @Column(name = "created_at", length = 60, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public User(String login, String firstname, String lastname, String role) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
