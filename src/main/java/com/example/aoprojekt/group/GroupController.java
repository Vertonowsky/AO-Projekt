package com.example.aoprojekt.group;

import com.example.aoprojekt.Common;
import com.example.aoprojekt.exception.EntityNotFoundException;
import com.example.aoprojekt.user.User;
import com.example.aoprojekt.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/groups")
@AllArgsConstructor
public class GroupController {

  private final UserRepository userRepository;

  @GetMapping()
  public ModelAndView groups(Authentication authentication, Model model) throws EntityNotFoundException {
    if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
      OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
      String email = oAuth2User.getAttribute("email");

      User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Użytkownik nie istnieje."));

      Set<Group> groups = new HashSet<>();
      if (!Common.isNullOrEmpty(user.getGroupUsers()))
        groups = user.getGroupUsers().stream().map((GroupUsers::getGroup)).collect(Collectors.toSet());

      model.addAttribute("groups", groups);
    }

    return new ModelAndView("group");
  }

}
