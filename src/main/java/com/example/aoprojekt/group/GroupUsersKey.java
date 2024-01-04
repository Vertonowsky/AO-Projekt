package com.example.aoprojekt.group;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class GroupUsersKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "group_id")
    private Long groupId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
