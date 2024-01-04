package com.example.aoprojekt.task;

import com.example.aoprojekt.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(long id);


    Set<Task> findAllByUser(@Param("user") User user);

}
