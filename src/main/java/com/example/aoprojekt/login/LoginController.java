package com.example.aoprojekt.login;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logowanie")
public class LoginController {

  @GetMapping
  public String groups(Authentication authentication, Model model) {

    return "logowanie";
  }
}

