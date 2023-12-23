package com.example.aoprojekt.user;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void processPostLogin(String email) {
    Optional<User> user = userRepository.findByEmail(email);

    if (user.isEmpty()) {
      userRepository.save(new User(email, false));
    }
  }
}
