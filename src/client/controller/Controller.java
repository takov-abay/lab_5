package client.controller;

import client.commands.Commands;
import server.data.CommandHistory;

import java.io.IOException;

/**
 * Process client.commands and pass it to command module
 *
 * @author Abay
 */
public class Controller {
    /**
     * Splits string with client.commands to command name and parameters
     *
     * @param command command to process
     */
    public Controller(String command) throws Exception {
        String[] nameCommands = new String[2];
        nameCommands = command.split(" ");
        if (nameCommands.length == 1) {
            Commands useCommands = new Commands(nameCommands[0], null);
        } else if (nameCommands.length == 2) {
            Commands useCommands = new Commands(nameCommands[0], nameCommands[1]);
        }
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand(nameCommands[0]);
    }
}
