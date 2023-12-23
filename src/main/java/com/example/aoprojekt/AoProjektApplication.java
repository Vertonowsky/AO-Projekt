package com.example.aoprojekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@Controller
public class AoProjektApplication {

  public static void main(String[] args) {
    SpringApplication.run(AoProjektApplication.class, args);
  }

  @GetMapping("/")
  public ModelAndView index(Authentication authentication, Model model) {

    if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
      OAuth2User user = (OAuth2User) authentication.getPrincipal();

      String email = user.getAttribute("email");
      String firstName = user.getAttribute("given_name");
      String lastName = user.getAttribute("family_name");

      System.out.println(email);
      System.out.println(firstName + " " + lastName);

      model.addAttribute("userEmail", email);
    }

    return new ModelAndView("index");
  }
}
