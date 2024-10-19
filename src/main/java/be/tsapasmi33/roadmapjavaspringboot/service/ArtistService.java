package be.tsapasmi33.roadmapjavaspringboot.service;

import be.tsapasmi33.roadmapjavaspringboot.model.Artist;
import be.tsapasmi33.roadmapjavaspringboot.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public Artist getArtist(long id) {
        return artistRepository.findById(id);
    }

    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public void updateArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public void deleteArtist(long id) {
        artistRepository.deleteById(id);
    }
}
