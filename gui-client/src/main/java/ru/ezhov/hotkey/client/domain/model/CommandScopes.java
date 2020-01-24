package ru.ezhov.hotkey.client.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandScopes {
    private List<CommandScope> commandScopes = new ArrayList<>();


    private CommandScopes(List<CommandScope> commandScopes) {
        this.commandScopes.addAll(commandScopes);
    }

    public static CommandScopes from(List<CommandScope> commandScopes) {
        return new CommandScopes(commandScopes);
    }

    public List<CommandScope> unmodifiableListCommandScopes() {
        return Collections.unmodifiableList(commandScopes);
    }

    public CommandScope commandScopeBy(int index) {
        return commandScopes.get(index);
    }

    public int size() {
        return commandScopes.size();
    }
}
