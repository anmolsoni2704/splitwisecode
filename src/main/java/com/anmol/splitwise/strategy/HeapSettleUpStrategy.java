package com.anmol.splitwise.strategy;

import com.anmol.splitwise.models.Expense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Expense> settleUp(List<Expense> expenses) {
        //iterate through all expenses and find out total extra/
        //lesser amount paid by every user involved.
        //Apply the max/min heap algorithm to settle up everyone.
        return null;
    }
}
