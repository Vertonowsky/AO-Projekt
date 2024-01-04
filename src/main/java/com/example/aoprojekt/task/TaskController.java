package com.example.aoprojekt.task;

import com.example.aoprojekt.exception.EntityNotFoundException;
import com.example.aoprojekt.user.User;
import com.example.aoprojekt.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

  private final TaskRepository taskRepository;

  private final UserRepository userRepository;

  @GetMapping()
  public ModelAndView tasks(Authentication authentication, Model model) throws EntityNotFoundException {

    if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
      OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
      String email = oAuth2User.getAttribute("email");
      User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Uzytkownik nie istnieje!"));

      model.addAttribute("tasks", taskRepository.findAllByUser(user));
    }

    return new ModelAndView("task");
  }
}
