package com.anmol.splitwise.repositories;

import com.anmol.splitwise.models.ExpenseUser;
import com.anmol.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {
    public List<ExpenseUser> findAllByUser(User user);
}
