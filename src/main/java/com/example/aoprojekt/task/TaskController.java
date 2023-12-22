package com.example.aoprojekt.task;

import com.example.aoprojekt.exception.EntityNotFoundException;
import com.example.aoprojekt.user.User;
import com.example.aoprojekt.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;


    @GetMapping("{id}")
    public Task get(@PathVariable("id") long id) throws EntityNotFoundException {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null)
            throw new EntityNotFoundException(String.format("Task with id %d doesn't exist!", id));

        return task;
    }

    @PostMapping
    public Task create(@Valid @RequestBody TaskDto taskDto) throws EntityNotFoundException {
        User user = userRepository.findById(taskDto.getUserId()).orElse(null);
        if (user == null)
            throw new EntityNotFoundException(String.format("User with id %d doesn't exist!", taskDto.getUserId()));

        return taskRepository.save(new Task(taskDto.getTitle(), user));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id) throws EntityNotFoundException {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null)
            throw new EntityNotFoundException(String.format("Task with id %s doesn't exist!", id));

        taskRepository.delete(task);
    }

    @GetMapping("list/{userId}")
    public Set<Task> list(@PathVariable("userId") long userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null)
            throw new EntityNotFoundException(String.format("User with id %d doesn't exist!", userId));

        return taskRepository.findAllByUser(user);
    }

}
