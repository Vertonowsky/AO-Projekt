package com.example.aoprojekt.group;

import com.example.aoprojekt.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "\"user_to_group\"")
public class GroupUsers {

    @EmbeddedId
    private GroupUsersKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Group group;


    public GroupUsersKey getId() {
        return id;
    }

    public void setId(GroupUsersKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}