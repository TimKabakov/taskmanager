package hell.prod.taskmanager.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Модель задания")
public class TaskDto {
    @Schema(description = "ID задания", required = true, example = "1")
    private Long id;
    @Schema(description = "Название задания", required = true, example = "Сюжет")
    private String name;
    @Schema(description = "Имя пользователя", required = true, example = "Тимофей")
    private String userName;
    @Schema(description = "Текст задания", required = true, example = "Собрать сюжет: текст сюжета")
    private String task;
    @Schema(description = "Статус задания", required = true, example = "В работе")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaskDto(Long id, String name, String userName, String task, String status) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.task = task;
        this.status = status;
    }
}
