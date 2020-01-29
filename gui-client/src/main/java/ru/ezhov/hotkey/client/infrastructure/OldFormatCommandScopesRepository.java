package ru.ezhov.hotkey.client.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ezhov.hotkey.client.domain.model.Command;
import ru.ezhov.hotkey.client.domain.model.CommandScope;
import ru.ezhov.hotkey.client.domain.model.CommandScopes;
import ru.ezhov.hotkey.client.domain.model.CommandScopesRepository;
import ru.ezhov.hotkey.client.domain.model.CommandScopesRepositoryException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OldFormatCommandScopesRepository implements CommandScopesRepository {

    private File file;
    private long id = 0;

    public OldFormatCommandScopesRepository(File file) {
        this.file = file;
    }

    @Override
    public long generateId() {
        return id++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CommandScopes all() throws CommandScopesRepositoryException {
        try (InputStream stream = new FileInputStream(file)) {
            byte[] b = new byte[stream.available()];
            stream.read(b);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Map<String, String>> myMap = objectMapper.readValue(new String(b, StandardCharsets.UTF_8), HashMap.class);
            List<CommandScope> commandScopes = new ArrayList<>();
            for (Map.Entry<String, Map<String, String>> entry : myMap.entrySet()) {
                String commandScopeName = entry.getKey();
                Map<String, String> commands = entry.getValue();
                List<Command> commandList = new ArrayList<>();
                for (Map.Entry<String, String> entryCommand : commands.entrySet()) {
                    String commandName = entryCommand.getKey();
                    String commandDescription = entryCommand.getValue();
                    commandList.add(Command.from(generateId(), commandName, commandDescription));
                }
                commandScopes.add(CommandScope.from(generateId(), commandScopeName, commandList));
            }
            return CommandScopes.from(commandScopes);
        } catch (Exception e) {
            throw new CommandScopesRepositoryException(e);
        }
    }

    @Override
    public void save(CommandScopes commandScopes) throws CommandScopesRepositoryException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Map<String, String>> myMap = new HashMap<>();

            List<CommandScope> scopes = commandScopes.unmodifiableListCommandScopes();
            for (CommandScope commandScope : scopes) {
                Map<String, String> map = new HashMap<>();

                List<Command> commands = commandScope.unmodifiableListCommands();
                for (Command command : commands) {
                    map.put(command.name(), command.description());
                }

                myMap.put(commandScope.name(), map);
            }


            objectMapper.writeValue(file, myMap);
        } catch (Exception e) {
            throw new CommandScopesRepositoryException(e);
        }
    }
}
