package ru.ezhov.hotkey.client.gui;

import ru.ezhov.hotkey.client.domain.model.Command;
import ru.ezhov.hotkey.client.domain.model.CommandScope;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CommandScopeTableModel extends AbstractTableModel {
    private CommandScope commandScope;

    public CommandScopeTableModel(CommandScope commandScope) {
        this.commandScope = commandScope;
    }

    @Override
    public int getRowCount() {
        return this.commandScope.unmodifiableListCommands().size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    public void addNewCommand() {
        commandScope.newCommand();
        this.fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    //TODO: сделать удаление строки
    public void deleteSelectedCommand() {
        commandScope.newCommand();
        this.fireTableRowsUpdated(getRowCount() - 1, getRowCount() - 1);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Ключ";
            case 1:
                return "Описание";
            default:
                throw new UnsupportedOperationException("Неподдерживаемый столбец");
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        List<Command> commands = commandScope.unmodifiableListCommands();
        String value = (String) aValue;
        Command command = commands.get(rowIndex);

        switch (columnIndex) {
            case 0:
                command.newName(value);
                break;
            case 1:
                command.newDescription(value);
                break;
            default:
                throw new UnsupportedOperationException("Неподдерживаемый стобец");
        }
        this.fireTableRowsUpdated(rowIndex, rowIndex);

    }

    @Override
    public Object getValueAt(int row, int column) {

        List<Command> commands = commandScope.unmodifiableListCommands();

        Command command = commands.get(row);
        switch (column) {
            case 0:
                return command.name();
            case 1:
                return command.description();
            default:
                throw new UnsupportedOperationException("неподдерживаемый номер столбца");
        }
    }
}
