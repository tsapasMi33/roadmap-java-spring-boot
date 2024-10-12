package be.tsapasmi33.roadmapjavaspringboot.repository;

import be.tsapasmi33.roadmapjavaspringboot.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist,Long> {

    List<Artist> findByLastname(String lastName);

    Artist findById(long id);
}
