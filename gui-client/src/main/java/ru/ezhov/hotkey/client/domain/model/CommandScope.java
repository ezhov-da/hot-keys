package ru.ezhov.hotkey.client.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public static CommandScope create(NewCommandScope newCommandScope) {
        List<Command> commands = newCommandScope
                .unmodifiableListCommands()
                .stream().map(ncs -> Command.from(-1L, ncs.name(), ncs.description()))
                .collect(Collectors.toList());

        return new CommandScope(-1L, newCommandScope.name(), commands);
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

    public void newEmptyCommand() {
        commands.add(Command.from(-1L, "", ""));
    }
}
