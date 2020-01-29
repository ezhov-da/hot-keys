package ru.ezhov.hotkey.client.infrastructure;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ru.ezhov.hotkey.client.domain.model.CommandScope;
import ru.ezhov.hotkey.client.domain.model.CommandScopes;

import java.io.File;
import java.util.List;

public class OldFormatCommandScopesRepositoryTest {

    @Test
    public void shouldParseOldFormatWithoutErrors() throws Exception {
        OldFormatCommandScopesRepository oldFormatCommandScopesRepository = new OldFormatCommandScopesRepository(new File("hot-keys.json"));
        CommandScopes commandScopes = oldFormatCommandScopesRepository.all();

        List<CommandScope> scopes = commandScopes.unmodifiableListCommandScopes();

        assertEquals(2, scopes.size());
        CommandScope commandScope = scopes.get(0);
        assertEquals(new Long(3), commandScope.id());
        assertEquals("Bash -> Объединение выражений", commandScope.name());
    }
}