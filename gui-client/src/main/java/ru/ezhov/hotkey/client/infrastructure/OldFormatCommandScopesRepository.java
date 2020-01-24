package ru.ezhov.hotkey.client.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ezhov.hotkey.client.domain.model.Command;
import ru.ezhov.hotkey.client.domain.model.CommandScope;
import ru.ezhov.hotkey.client.domain.model.CommandScopes;
import ru.ezhov.hotkey.client.domain.model.CommandScopesRepository;
import ru.ezhov.hotkey.client.domain.model.CommandScopesRepositoryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OldFormatCommandScopesRepository implements CommandScopesRepository {

    private String data;
    private long id = 0;

    public OldFormatCommandScopesRepository(String data) {
        this.data = data;
    }

    @Override
    public long generateId() {
        return id++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CommandScopes all() throws CommandScopesRepositoryException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Map<String, String>> myMap = objectMapper.readValue(data, HashMap.class);
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
}
