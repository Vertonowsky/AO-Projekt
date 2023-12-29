package com.example.aoprojekt.group;

import com.example.aoprojekt.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
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

  @NonNull private String name;

  @NonNull private String description;

  @ManyToMany private List<User> users;

  @NonNull private Date createdAt;
}
