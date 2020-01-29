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
        List<CommandScope> scopes = new ArrayList<>(commandScopes);
        scopes.sort(new CommandScopeComparator());

        return new CommandScopes(scopes);
    }

    public void addNewCommandScope(NewCommandScope newCommandScope) {


        this.commandScopes.add(CommandScope.create(newCommandScope));
        commandScopes.sort(new CommandScopeComparator());
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
