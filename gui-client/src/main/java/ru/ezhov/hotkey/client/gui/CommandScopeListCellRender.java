package ru.ezhov.hotkey.client.gui;

import ru.ezhov.hotkey.client.domain.model.CommandScope;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Component;

public class CommandScopeListCellRender extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        CommandScope commandScope = (CommandScope) value;
        label.setText(commandScope.name());

        return label;
    }
}
