package com.example.aoprojekt.task;

import com.example.aoprojekt.user.User;
import com.example.aoprojekt.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

  private final TaskRepository taskRepository;

  private final UserRepository userRepository;

  @GetMapping("{userId}")
  public String tasks(
      @PathVariable("userId") long userId, Authentication authentication, Model model) {
    boolean isAdmin = false;

    if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
      OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
      String email = oAuth2User.getAttribute("email");

      isAdmin = true;

      User user = userRepository.findByEmail(email).orElseThrow();

      System.out.println(taskRepository.findAllByUser(user));

      model.addAttribute("tasks", taskRepository.findAllByUser(user));
    }

    return "task";
  }
}
