package ru.ezhov.hotkey.client.domain.model;

import java.util.Comparator;

public class CommandScopeComparator implements Comparator<CommandScope> {
    @Override
    public int compare(CommandScope o1, CommandScope o2) {
        return o1.name().compareTo(o2.name());
    }
}
