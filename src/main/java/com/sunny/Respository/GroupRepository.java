package com.sunny.Respository;


import com.sunny.Model.Expense;
import com.sunny.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Expense> findGroupById(Long groupId);
}
