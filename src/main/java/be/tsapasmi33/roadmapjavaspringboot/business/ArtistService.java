package be.tsapasmi33.roadmapjavaspringboot.business;

import be.tsapasmi33.roadmapjavaspringboot.model.Artist;
import be.tsapasmi33.roadmapjavaspringboot.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

//    instead of using @Autowired. we can inject the dep via the constructor. (Spring manages the injection)
//    Later I will be using Lombok's @RequiredArgsConstructor for DI
//    Mentionned since different from Roadmap recommendation
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtists(){
        List<Artist> artists = new ArrayList<>();
        artistRepository.findAll().forEach(artists::add);
        return artists;
    }

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
