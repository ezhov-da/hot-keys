package ru.ezhov.hotkey.client.gui;

import ru.ezhov.hotkey.client.domain.model.CommandScope;
import ru.ezhov.hotkey.client.domain.model.CommandScopesRepository;
import ru.ezhov.hotkey.client.domain.model.CommandScopesRepositoryException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//TODO: Сделать сохранение файла

public class BasicPanel extends JPanel {
    private JTextField textFieldSearch;
    private JList<CommandScope> commandScopeJList;
    private CommandScopeListModel commandScopeListModel;
    private CommandScopesRepository commandScopesRepository;

    private CommandScopePanel commandScopePanel;
    private JSplitPane splitPane = new JSplitPane();

    public BasicPanel(CommandScopesRepository commandScopesRepository) throws CommandScopesRepositoryException {
        super(new BorderLayout());

        this.commandScopesRepository = commandScopesRepository;
        this.textFieldSearch = new JTextField();

        commandScopeListModel = new CommandScopeListModel(commandScopesRepository.all());
        this.commandScopeJList = new JList<>();
        this.commandScopeJList.setModel(commandScopeListModel);
        this.commandScopeJList.setCellRenderer(new CommandScopeListCellRender());

        this.commandScopeJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                actionOnSelectedList();
            }
        });

        this.commandScopeJList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                actionOnSelectedList();
            }
        });

        commandScopePanel = new CommandScopePanel();

        splitPane.setLeftComponent(commandScopePanel);

        this.add(splitPane, BorderLayout.CENTER);
    }

    private void actionOnSelectedList() {
        SwingUtilities.invokeLater(() -> {
            CommandScope selectedValue = commandScopeJList.getSelectedValue();
            if (selectedValue != null) {
                splitPane.setRightComponent(new CommandPanel(selectedValue));
            }
        });
    }

    private class CommandScopePanel extends JPanel {
        private JToolBar toolBar;

        public CommandScopePanel() {
            super(new BorderLayout());
            toolBar = new JToolBar(JToolBar.VERTICAL);
            toolBar.setFloatable(false);
            this.add(toolBar, BorderLayout.WEST);
            toolBar.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "+");
                }

                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            toolBar.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "-");
                }

                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            this.add(new JScrollPane(commandScopeJList), BorderLayout.CENTER);
        }
    }

    private class CommandPanel extends JPanel {
        private JToolBar toolBar;
        private CommandScope commandScope;

        private JTable table;

        public CommandPanel(CommandScope commandScope) {
            super(new BorderLayout());

            this.commandScope = commandScope;

            CommandScopeTableModel commandScopeTableModel = new CommandScopeTableModel(this.commandScope);

            toolBar = new JToolBar();
            toolBar.setFloatable(false);
            this.add(toolBar, BorderLayout.NORTH);
            toolBar.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "+");
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(commandScopeTableModel::addNewCommand);
                }
            });
            toolBar.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "-");
                }

                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });


            this.table = new JTable();
            this.table.setModel(commandScopeTableModel);

            this.add(new JScrollPane(table), BorderLayout.CENTER);

        }
    }
}
