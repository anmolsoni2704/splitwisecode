package com.anmol.splitwise.repositories;

import com.anmol.splitwise.models.Expense;
import com.anmol.splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByGroup(Group group);
}
