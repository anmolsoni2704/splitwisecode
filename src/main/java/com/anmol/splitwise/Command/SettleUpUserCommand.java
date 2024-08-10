package com.anmol.splitwise.Command;

import com.anmol.splitwise.controllers.SettleUpController;
import com.anmol.splitwise.dtos.SettleUpUserRequestDTO;
import com.anmol.splitwise.dtos.SettleUpUserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpUserCommand implements Command{
    SettleUpController settleUpController;

    public SettleUpUserCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {
        //1234 settleUp => No of words -> 2
        List<String> words = List.of(input.split(" "));

        return words.size() == 2 && words.get(1).equalsIgnoreCase(CommandKeywords.SettleCommand);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));

        Long userId = Long.valueOf(words.get(0));

        SettleUpUserRequestDTO settleUpUserRequestDTO = new SettleUpUserRequestDTO();
        settleUpUserRequestDTO.setUserId(userId);

        SettleUpUserResponseDTO settleUpUserResponseDTO = settleUpController.settleUpUser(settleUpUserRequestDTO);
    }
}
