package com.example.aoprojekt.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface GroupRepository extends JpaRepository<Group, Long> {


    Set<Group> findAllByIdIn(@Param("ids") List<Long> ids);


}
