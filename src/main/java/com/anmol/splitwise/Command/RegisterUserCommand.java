package com.anmol.splitwise.Command;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterUserCommand implements Command{
    @Override
    public boolean matches(String input) {
        //Register anmol 003 hakunamatata
        List<String> words = List.of(input.split(" "));

        return words.size() == 4 && words.get(0).equalsIgnoreCase(CommandKeywords.RegisterCommand);
    }

    @Override
    public void execute(String input) {

    }
}
