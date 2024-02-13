package hell.prod.taskmanager.core.utils;

public enum TaskStatus {
    CREATED("created"),
    INPROGRESS("in progress"),
    DONE("done"),
    NEEDREWORK("need rework"),
    POSTPONED("postponed");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
    public static TaskStatus fromString(String name){
        for(TaskStatus t: TaskStatus.values()){
            if(t.name().equalsIgnoreCase(name)){
                return t;
            }
        }
        throw new IllegalArgumentException("No such status" + name);
    }
}
