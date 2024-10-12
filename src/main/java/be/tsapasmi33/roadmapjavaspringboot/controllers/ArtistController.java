package be.tsapasmi33.roadmapjavaspringboot.controllers;

import be.tsapasmi33.roadmapjavaspringboot.business.ArtistService;
import be.tsapasmi33.roadmapjavaspringboot.model.Artist;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/artists/{id:[0-9]+}/edit")
    public String edit(Model model, @PathVariable("id") long id, HttpServletRequest request) {
        Artist artist = artistService.getArtist(id);

        model.addAttribute("artist",artist);

        String referer = request.getHeader("Referer");

        if (referer != null && !referer.isEmpty()) {
            model.addAttribute("back", referer);
        } else {
            model.addAttribute("back", "/artists" + artist.getId());
        }

        return "artist/edit";
    }

    @PutMapping("/artists/{id:[0-9]+}/edit")
    public String update(Model model, @PathVariable("id") long id, @Valid @ModelAttribute("artist") Artist artist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "artist/edit";
        }

        Artist existing = artistService.getArtist(id);

        if (existing == null) {
            return "artist/index";
        }

        artistService.updateArtist(artist);

        return "redirect:/artists/" + artist.getId();
    }
}
