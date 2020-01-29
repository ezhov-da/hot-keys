package ru.ezhov.hotkey.client.domain.model;

public class Command {
    private Long id;
    private String name;
    private String description;

    private Command(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Command from(Long id, String name, String description) {
        return new Command(id, name, description);
    }



    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public void newName(String name) {
        this.name = name;
    }

    public void newDescription(String description) {
        this.description = description;
    }
}
