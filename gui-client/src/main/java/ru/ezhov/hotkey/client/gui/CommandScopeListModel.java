package ru.ezhov.hotkey.client.gui;

import ru.ezhov.hotkey.client.domain.model.CommandScope;
import ru.ezhov.hotkey.client.domain.model.CommandScopes;

import javax.swing.AbstractListModel;

//TODO: сделать удаление и добавление строк
public class CommandScopeListModel extends AbstractListModel<CommandScope> {

    private CommandScopes commandScopes;

    public CommandScopeListModel(CommandScopes commandScopes) {
        this.commandScopes = commandScopes;
    }

    @Override
    public int getSize() {
        return commandScopes.size();
    }

    @Override
    public CommandScope getElementAt(int index) {
        return commandScopes.commandScopeBy(index);
    }
}
