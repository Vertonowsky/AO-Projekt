package com.example.aoprojekt.user;

import com.example.aoprojekt.exception.EntityNotFoundException;
import com.example.aoprojekt.group.GroupUsers;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserRepository userRepository;

  @GetMapping
  public ModelAndView users(Authentication authentication, Model model) {
    boolean isAdmin = false;

    if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
      OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
      String email = oAuth2User.getAttribute("email");

      User user = userRepository.findByEmail(email).orElse(null);
      isAdmin = isAdmin(email);

      model.addAttribute("title", "Wszyscy użytkownicy!");
      model.addAttribute("userId", user == null ? -1 : user.getId());
    }

    model.addAttribute("users", userRepository.findAll());
    model.addAttribute("isAdmin", isAdmin);
    return new ModelAndView("user");
  }


  public boolean isAdmin(String email) {
    User user = userRepository.findByEmail(email).orElse(null);
    if (user == null) return false;

    return user.isAdmin();
  }
}
