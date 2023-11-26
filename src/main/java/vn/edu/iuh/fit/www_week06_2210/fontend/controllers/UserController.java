package vn.edu.iuh.fit.www_week06_2210.fontend.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.www_week06_2210.backend.models.User;
import vn.edu.iuh.fit.www_week06_2210.backend.repositories.UserRepository;

import java.util.Optional;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = {"/login"})
    public String login(Model model, HttpSession session) {
        User user = new User();

        if (session.getAttribute("user") != null)
            return "redirect:/index";

        model.addAttribute("user", user);

        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());

        if(userOptional != null){
            httpSession.setAttribute("user", user);
            return "redirect:/index";
        }

        return "/login";
    }

}
