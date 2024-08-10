package com.anmol.splitwise.Command;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateGroupCommand implements Command{
    @Override
    public boolean matches(String input) {
        // u1 AddGroup roomates
        List<String> words = List.of(input.split(" "));

        return words.size() == 3 && words.get(0).equalsIgnoreCase(CommandKeywords.AddGroupCommand);
    }

    @Override
    public void execute(String input) {

    }
}
