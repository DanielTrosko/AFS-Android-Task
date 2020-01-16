package it.danieltrosko.task;

public class Task {
    private String name;
    private StatusType status;
    private String changeStatus;

    public Task(String name, StatusType status, String changeStatus) {
        this.name = name;
        this.status = status;
        this.changeStatus = changeStatus;
    }

    public String getName() {
        return name;
    }


    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }
}
