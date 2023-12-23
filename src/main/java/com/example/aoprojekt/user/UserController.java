package com.example.aoprojekt.user;

import com.example.aoprojekt.exception.ConflictException;
import com.example.aoprojekt.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public User get(@PathVariable("id") long id) throws EntityNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw new EntityNotFoundException(String.format("User with id %d doesn't exist!", id));

        return user;
    }

    @PostMapping
    public User create(@RequestBody String email) throws ConflictException {
        if (userRepository.findByEmail(email).isPresent())
            throw new ConflictException("User already exists!");

        return userRepository.save(new User(email, false));
    }

    @DeleteMapping("/{email}")
    public void delete(@PathVariable("email") String email) throws EntityNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null)
            throw new EntityNotFoundException(String.format("User with email %s doesn't exist!", email));

        userRepository.delete(user);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userRepository.findAll();
    }

}
