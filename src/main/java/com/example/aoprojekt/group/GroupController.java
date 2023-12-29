package com.example.aoprojekt.group;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/groups")
@AllArgsConstructor
public class GroupController {

  private final GroupRepository groupRepository;

  @GetMapping
  public String groups(Authentication authentication, Model model) {

    model.addAttribute("groups", groupRepository.findAll());

    return "group";
  }
}
