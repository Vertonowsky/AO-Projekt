package com.example.aoprojekt;

import com.example.aoprojekt.user.User;
import com.example.aoprojekt.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@Controller
@AllArgsConstructor
public class AoProjektApplication {

  private final UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(AoProjektApplication.class, args);
  }

  @GetMapping("/")
  public ModelAndView index(Authentication authentication, Model model) {
    boolean isAdmin = false;

    if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
      OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

      String email = oAuth2User.getAttribute("email");
      String firstName = oAuth2User.getAttribute("given_name");
      String lastName = oAuth2User.getAttribute("family_name");

      System.out.println(email);
      System.out.println(firstName + " " + lastName);

      User user = userRepository.findByEmail(email).orElse(null);
      isAdmin = isAdmin(email);

      model.addAttribute("userEmail", email);
      model.addAttribute("title", String.format("Witaj %s!", email));
      model.addAttribute("userId", user == null ? -1 : user.getId());
    }

    model.addAttribute("isAdmin", isAdmin);

    return new ModelAndView("index");
  }

  @GetMapping("/tasks")
  public ModelAndView tasks(Authentication authentication, Model model) {
    Boolean isAdmin = false;

    if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
      OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
      String email = oAuth2User.getAttribute("email");

      User user = userRepository.findByEmail(email).orElse(null);
      isAdmin = isAdmin(email);

      model.addAttribute("title", "Wszystkie zadania!");
      model.addAttribute("userId", user == null ? -1 : user.getId());
      model.addAttribute("allTasks", true);
    }

    model.addAttribute("isAdmin", isAdmin);
    return new ModelAndView("task");
  }

  @GetMapping("/tasks/{userId}")
  public ModelAndView tasks(
      @PathVariable("userId") long userId, Authentication authentication, Model model) {
    boolean isAdmin = false;

    if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
      OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
      String email = oAuth2User.getAttribute("email");

      isAdmin = isAdmin(email);

      // TODO sprawdzic czy taki user istnieje (userRepository) - w innym wypadku nie ma zadnych
      // zadan

      model.addAttribute("title", String.format("Zadania uzytkownika %s!", email));
      model.addAttribute("userId", userId);
    }

    model.addAttribute("isAdmin", isAdmin);
    return new ModelAndView("task");
  }

  @GetMapping("/users")
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

    // TODO jak ktos nie ma uprawnien to powinno go wyrzucic do strony z logowaniem

    model.addAttribute("isAdmin", isAdmin);
    return new ModelAndView("user");
  }

  public boolean isAdmin(String email) {
    User user = userRepository.findByEmail(email).orElse(null);
    if (user == null) return false;

    return user.isAdmin();
  }
}
