package be.tsapasmi33.roadmapjavaspringboot.controller;

import be.tsapasmi33.roadmapjavaspringboot.model.Artist;
import be.tsapasmi33.roadmapjavaspringboot.service.ArtistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/artists")
    public String index(Model model) {
        List<Artist> artists = artistService.getAllArtists();

        model.addAttribute("artists",artists);
        model.addAttribute("title","Artists List");

        return "artist/index";
    }

    @GetMapping("/artists/{id:[0-9]+}")
    @PreAuthorize("hasRole('ROLE_member')")
    public String show(Model model, @PathVariable("id") long id) {
        Artist artist = artistService.getArtist(id);

        model.addAttribute("artist",artist);
        model.addAttribute("title", "Artist Details");

        return "artist/show";
    }

    @GetMapping("/artists/{id:[0-9]+}/edit")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String edit(Model model, @PathVariable("id") long id, HttpServletRequest request) {
        Artist artist = artistService.getArtist(id);

        model.addAttribute("artist",artist);

        String referer = request.getHeader("Referer");

        if (referer != null && !referer.isEmpty()) {
            model.addAttribute("back", referer);
        } else {
            model.addAttribute("back", "/artists/" + artist.getId());
        }

        return "artist/edit";
    }

    @PutMapping("/artists/{id:[0-9]+}/edit")
    @PreAuthorize("hasRole('ROLE_admin')")
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

    @GetMapping("/artists/create")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String create(Model model) {
        Artist artist = new Artist(null, null);
        model.addAttribute("artist",artist);
        model.addAttribute("back", "/artists");
        return "artist/create";
    }

    @PostMapping("/artists/create")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String store(@Valid @ModelAttribute("artist") Artist artist, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "artist/create";
        }


        artistService.addArtist(artist);
        return "redirect:/artists/" + artist.getId();
    }

    @DeleteMapping("/artists/{id:[0-9]+}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String delete(Model model, @PathVariable("id") long id) {
        Artist existing = artistService.getArtist(id);

        if (existing != null) {
            artistService.deleteArtist(id);
        }

        return "redirect:/artists";
    }
}
