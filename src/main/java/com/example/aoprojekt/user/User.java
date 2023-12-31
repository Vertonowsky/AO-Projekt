package com.example.aoprojekt.user;

import com.example.aoprojekt.task.Task;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private boolean isAdmin;

    private Date createdAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Task> tasks;

    public User() {

    }

    public User(String email, boolean isAdmin) {
        this.email = email;
        this.isAdmin = isAdmin;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Set<Task> getTasks() {
        return tasks;
    }
}
