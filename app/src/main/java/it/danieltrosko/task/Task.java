package it.danieltrosko.task;

public class Task {
    private String name;
    private String id;
    private String button;


    public Task(String name, String id, String button) {
        this.name = name;
        this.id = id;
        this.button = button;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
