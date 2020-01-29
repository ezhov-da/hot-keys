package ru.ezhov.hotkey.client.gui;

import ru.ezhov.hotkey.client.domain.model.CommandScopesRepository;
import ru.ezhov.hotkey.client.domain.model.CommandScopesRepositoryException;
import ru.ezhov.hotkey.client.infrastructure.OldFormatCommandScopesRepository;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.io.File;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
        {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Throwable ex) {
                //
            }
            try {
                JFrame frame = new JFrame("HoKo editor");

                frame.setIconImage(new ImageIcon(Application.class.getResource("/text-editor_24x24.png")).getImage());

                CommandScopesRepository commandScopesRepository = new OldFormatCommandScopesRepository(new File("hot-keys.json"));

                BasicPanel basicPanel = null;
                basicPanel = new BasicPanel(frame, commandScopesRepository);

                frame.add(basicPanel, BorderLayout.CENTER);

                frame.setSize(1000, 600);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (CommandScopesRepositoryException e) {
                e.printStackTrace();
            }
        });
    }
}

