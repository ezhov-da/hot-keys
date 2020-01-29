package ru.ezhov.hotkey.client.domain.model;

public class NewCommand {
    private String name;
    private String description;

    private NewCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static NewCommand create(String name, String description) {
        return new NewCommand(name, description);
    }

    public String name() {
        return name;
    }

    public static NewCommand empty() {
        return new NewCommand("", "");
    }

    public String description() {
        return description;
    }
}
