package be.tsapasmi33.roadmapjavaspringboot.controller;

import be.tsapasmi33.roadmapjavaspringboot.model.User;
import be.tsapasmi33.roadmapjavaspringboot.service.UserService;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String index(Model model) {
        List<User> users = userService.getAllUsers();

        model.addAttribute("users",users);
        model.addAttribute("title","Users List");

        return "user/index";
    }

    @GetMapping("/users/{id:[0-9]+}")
    public String show(Model model, @PathVariable("id") long id) {
        User user = userService.getUser(id);

        model.addAttribute("user",user);
        model.addAttribute("title", "User Details");

        return "user/show";
    }

    @GetMapping("/users/{id:[0-9]+}/edit")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String edit(Model model, @PathVariable("id") long id, HttpServletRequest request) {
        User user = userService.getUser(id);

        model.addAttribute("user",user);

        String referer = request.getHeader("Referer");

        if (referer != null && !referer.isEmpty()) {
            model.addAttribute("back", referer);
        } else {
            model.addAttribute("back", "/users/" + user.getId());
        }

        return "user/edit";
    }

    @PutMapping("/users/{id:[0-9]+}/edit")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String update(Model model, @PathVariable("id") long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        User existing = userService.getUser(id);

        if (existing == null) {
            return "artist/index";
        }

        existing.setLogin(user.getLogin());
        existing.setFirstname(user.getFirstname());
        existing.setLastname(user.getLastname());
        existing.setRole(user.getRole());

        userService.updateUser(id, existing);

        return "redirect:/users/" + user.getId();
    }

    @DeleteMapping("/users/{id:[0-9]+}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String delete(Model model, @PathVariable("id") long id) {
        User existing = userService.getUser(id);

        if (existing != null) {
            userService.deleteUser(id);
        }

        return "redirect:/users";
    }
}
