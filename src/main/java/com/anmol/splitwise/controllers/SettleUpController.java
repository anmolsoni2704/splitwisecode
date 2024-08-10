package com.anmol.splitwise.controllers;

import com.anmol.splitwise.dtos.SettleUpGroupRequestDTO;
import com.anmol.splitwise.dtos.SettleUpGroupResponseDTO;
import com.anmol.splitwise.dtos.SettleUpUserRequestDTO;
import com.anmol.splitwise.dtos.SettleUpUserResponseDTO;
import com.anmol.splitwise.models.Expense;
import com.anmol.splitwise.services.SettleUpService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class SettleUpController {
    private final SettleUpService settleUpService;

    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleUpUserResponseDTO settleUpUser(SettleUpUserRequestDTO settleUpUserRequestDTO) {
        List< Expense> expenseList = settleUpService.settleUpUser(settleUpUserRequestDTO.getUserId());

        SettleUpUserResponseDTO settleUpUserResponseDTO = new SettleUpUserResponseDTO();
        settleUpUserResponseDTO.setTransactionsList(expenseList);
        return settleUpUserResponseDTO;
    }

    public SettleUpGroupResponseDTO settleUpGroup(SettleUpGroupRequestDTO settleUpGroupRequestDTO) {
        List<Expense> expenseList = settleUpService.settleUpGroup(settleUpGroupRequestDTO.getGroupId());

        SettleUpGroupResponseDTO settleUpGroupResponseDTO = new SettleUpGroupResponseDTO();
        settleUpGroupResponseDTO.setTransactionsList(expenseList);
        return settleUpGroupResponseDTO;
    }
}
