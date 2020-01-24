package ru.ezhov.hotkey.client.domain.model;

import java.util.Collections;
import java.util.List;

public class CommandScope {
    private Long id;
    private String name;

    private List<Command> commands;

    private CommandScope(Long id, String name, List<Command> commands) {
        this.id = id;
        this.name = name;
        this.commands = commands;
    }

    public static CommandScope from(Long id, String name, List<Command> commands) {
        return new CommandScope(id, name, commands);
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<Command> unmodifiableListCommands() {
        return Collections.unmodifiableList(commands);
    }

    public void newCommand() {
        commands.add(Command.empty());
    }
}
