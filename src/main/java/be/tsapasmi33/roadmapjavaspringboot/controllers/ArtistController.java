package be.tsapasmi33.roadmapjavaspringboot.controllers;

import be.tsapasmi33.roadmapjavaspringboot.business.ArtistService;
import be.tsapasmi33.roadmapjavaspringboot.model.Artist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public String index(Model model) {
        List<Artist> artists = artistService.getAllArtists();

        model.addAttribute("artists",artists);
        model.addAttribute("title","Liste des Artistes");

        return "artist/index";
    }

    @GetMapping("/artists/{id:[0-9]+}")
    public String show(Model model, @PathVariable("id") long id) {
        Artist artist = artistService.getArtist(id);

        model.addAttribute("artist",artist);
        model.addAttribute("title", "Fiche d'un artiste");

        return "artist/show";
    }
}
