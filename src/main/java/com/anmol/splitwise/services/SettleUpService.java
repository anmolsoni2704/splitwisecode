package com.anmol.splitwise.services;

import com.anmol.splitwise.models.Expense;
import com.anmol.splitwise.models.ExpenseUser;
import com.anmol.splitwise.models.Group;
import com.anmol.splitwise.models.User;
import com.anmol.splitwise.repositories.ExpenseRepository;
import com.anmol.splitwise.repositories.ExpenseUserRepository;
import com.anmol.splitwise.repositories.GroupRepository;
import com.anmol.splitwise.repositories.UserRepository;
import com.anmol.splitwise.strategy.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SettleUpService {
    private final UserRepository userRepository;
    private final ExpenseUserRepository expenseUserRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final SettleUpStrategy settleUpStrategy;

    public SettleUpService(UserRepository userRepository, ExpenseUserRepository expenseUserRepository, GroupRepository groupRepository, ExpenseRepository expenseRepository, SettleUpStrategy settleUpStrategy) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    public List<Expense> settleUpUser(Long userId){
        /*
        1. Get the user object from User table.
        2. Get all expenses the user was a part of.
        3. Iterate through all expenses and find out total extra/
        4. lesser amount paid by every user involved.
        5. Apply the max/min heap algorithm to settle up everyone.
         */

        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new RuntimeException("User with user id " + userId +" not found");
        }

        User user = optionalUser.get();

        List<ExpenseUser> expenseUserList = expenseUserRepository.findAllByUser(user);

        Set<Expense> expenses = new HashSet<>();
        for(ExpenseUser expenseUser : expenseUserList){
            expenses.add(expenseUser.getExpense());
        }

        List<Expense> transactionsToBeDone = settleUpStrategy.settleUp(expenses.stream().toList());

        List<Expense> expensesToReturn = new ArrayList<>();

        for(Expense expense : transactionsToBeDone){
            for(ExpenseUser expenseUser : expense.getExpenseUsers()){
                if(expenseUser.getUser().equals(user)){
                    expensesToReturn.add(expenseUser.getExpense());
                }
            }
        }

        return expensesToReturn;
    }

    public List<Expense> settleUpGroup(Long groupId){
        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        if(optionalGroup.isEmpty()){
            throw new RuntimeException("Group with group id " + groupId + " not found");
        }

        Group group = optionalGroup.get();

        List<Expense> expenses = expenseRepository.findAllByGroup(group);
        return settleUpStrategy.settleUp(expenses);
    }
}
