package ru.ezhov.hotkey.client.gui;

import ru.ezhov.hotkey.client.domain.model.NewCommandScope;

public interface ImportCallback {
    void doImport(NewCommandScope newCommandScope);
}
