package com.anmol.splitwise.Command;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandExecutor {
    private RegisterUserCommand registerUserCommand;
    private SettleUpUserCommand settleUpUserCommand;
    private CreateGroupCommand createGroupCommand;

    public CommandExecutor(RegisterUserCommand registerUserCommand, SettleUpUserCommand settleUpUserCommand, CreateGroupCommand createGroupCommand) {
        this.registerUserCommand = registerUserCommand;
        this.settleUpUserCommand = settleUpUserCommand;
        this.createGroupCommand = createGroupCommand;
    }

    public List<Command> commands = List.of(registerUserCommand, settleUpUserCommand, createGroupCommand);

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void removeCommand(Command command) {
        commands.remove(command);
    }

    public void execute(String input){
        for(Command command : commands){
            if(command.matches(input)){
                command.execute(input);
                break;
            }
        }
    }
}
