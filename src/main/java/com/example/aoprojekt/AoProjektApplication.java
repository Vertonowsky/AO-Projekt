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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
      model.addAttribute("isAdmin", isAdmin);
      return new ModelAndView("task");
    } else {
      return new ModelAndView(new RedirectView("/login", true));
    }
  }

  public boolean isAdmin(String email) {
    User user = userRepository.findByEmail(email).orElse(null);
    if (user == null) return false;

    return user.isAdmin();
  }

  @GetMapping("/login")
  public ModelAndView login(Authentication authentication, Model model) {

    // TODO Strona z logowaniem (Sam google czy może jeszcze inna opcja?)

    return new ModelAndView("login");
  }
}
