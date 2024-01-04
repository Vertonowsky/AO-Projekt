package com.example.aoprojekt.group;

import com.example.aoprojekt.task.Task;
import com.example.aoprojekt.user.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "\"group\"")
@Data
@RequiredArgsConstructor
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NonNull
  private String name;

  @NonNull
  private String description;

  @NonNull
  private Date createdAt;

  @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
  Set<GroupUsers> groupUsers;

  @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
  private Set<Task> tasks;

  public Group() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<GroupUsers> getGroupUsers() {
    return groupUsers;
  }

  public void setGroupUsers(Set<GroupUsers> groupUsers) {
    this.groupUsers = groupUsers;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
