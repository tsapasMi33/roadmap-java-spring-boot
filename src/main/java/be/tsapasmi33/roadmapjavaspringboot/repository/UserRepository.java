package be.tsapasmi33.roadmapjavaspringboot.repository;

import be.tsapasmi33.roadmapjavaspringboot.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByLogin(String login);
    List<User> findByLastname(String lastName);

    User findById(long id);
}
