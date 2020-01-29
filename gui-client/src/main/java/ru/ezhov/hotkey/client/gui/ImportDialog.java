package ru.ezhov.hotkey.client.gui;

import ru.ezhov.hotkey.client.domain.model.NewCommand;
import ru.ezhov.hotkey.client.domain.model.NewCommandScope;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

public class ImportDialog extends JDialog {

    private Frame owner;
    private ImportPanel importPanel = new ImportPanel();
    private ImportCallback importCallback;

    public ImportDialog(Frame owner) {
        super(owner, true);
        this.setTitle("Import");
        this.owner = owner;
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.add(importPanel, BorderLayout.CENTER);
    }

    public void showImport(String scopeName) {
        this.importPanel.init(scopeName);
        this.setVisible(true);
    }

    public void addImportCallback(ImportCallback importCallback) {
        this.importCallback = importCallback;
    }

    @Override
    public void setVisible(boolean b) {
        this.setSize(owner.getWidth() - 200, owner.getHeight() - 200);
        this.setLocationRelativeTo(owner);
        super.setVisible(b);
    }

    private class ImportPanel extends JPanel {
        private JTextField textField;
        private JTextPane textPane;
        private JButton button = new JButton("Import");

        public ImportPanel() {
            super(new BorderLayout());
            textField = new JTextField();
            textPane = new JTextPane();

            add(textField, BorderLayout.NORTH);
            add(new JScrollPane(textPane), BorderLayout.CENTER);
            add(new JScrollPane(button), BorderLayout.SOUTH);

            button.addActionListener(e -> {
                SwingUtilities.invokeLater(() -> {
                    List<NewCommand> newCommands = new ArrayList<>();

                    String text = textPane.getText();
                    String[] split = text.split("\n");
                    for (String s : split) {
                        String[] strings = s.split("\t");
                        if (strings.length >= 2) {
                            newCommands.add(NewCommand.create(strings[0], strings[1]));
                        }
                    }

                    if (importCallback != null) {
                        importCallback.doImport(NewCommandScope.create(textField.getText(), newCommands));
                    }

                });
            });
        }

        public void init(String scopeName) {
            textField.setText(scopeName);
        }
    }
}
