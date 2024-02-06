package hell.prod.taskmanager.core.utils;

public enum taskStatus {
    CREATED("created"),
    INPROGRESS("in progress"),
    DONE("done"),
    NEEDREWORK("need rework"),
    POSTPONED("postponed");

    private final String status;

    taskStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
