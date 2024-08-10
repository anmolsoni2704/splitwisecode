package com.anmol.splitwise.dtos;

import com.anmol.splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpGroupResponseDTO {
    private List<Expense> transactionsList;
}
