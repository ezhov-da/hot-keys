package ru.ezhov.hotkey.client.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewCommandScope {
    private String name;
    private List<NewCommand> commands;

    private NewCommandScope(String name, List<NewCommand> commands) {
        this.name = name;
        this.commands = commands;
    }

    public static NewCommandScope create(String name, List<NewCommand> commands) {
        return new NewCommandScope(name, commands);
    }

    public static NewCommandScope create(String name) {
        return new NewCommandScope(name, new ArrayList<>());
    }

    public String name() {
        return name;
    }

    public List<NewCommand> unmodifiableListCommands() {
        return Collections.unmodifiableList(commands);
    }
}
