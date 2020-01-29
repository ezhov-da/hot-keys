package ru.ezhov.hotkey.client.domain.model;

public interface CommandScopesRepository {
    long generateId();

    CommandScopes all() throws CommandScopesRepositoryException;

    void save(CommandScopes commandScopes) throws CommandScopesRepositoryException;
}
