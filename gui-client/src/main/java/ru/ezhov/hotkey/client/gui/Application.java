package ru.ezhov.hotkey.client.gui;

import ru.ezhov.hotkey.client.domain.model.CommandScopesRepository;
import ru.ezhov.hotkey.client.infrastructure.OldFormatCommandScopesRepository;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
        {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Throwable ex) {
                //
            }
            JFrame frame = new JFrame("Hot-Keys editor");

            try (InputStream stream = new FileInputStream(new File("hot-keys.json"));) {
                byte[] b = new byte[stream.available()];
                stream.read(b);

                CommandScopesRepository commandScopesRepository = new OldFormatCommandScopesRepository(new String(b, StandardCharsets.UTF_8));

                BasicPanel basicPanel = new BasicPanel(commandScopesRepository);

                frame.add(basicPanel, BorderLayout.CENTER);

                frame.setSize(1000, 600);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

