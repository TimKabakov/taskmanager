package hell.prod.taskmanager.core.converter;

import hell.prod.taskmanager.core.entities.Task;
import hell.prod.taskmanager.dto.TaskDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component

public class TaskConverter {

    public TaskDto entityToDto(@NotNull Task task){
        return new TaskDto(task.getId(), task.getName(), task.getUser().getName(), task.getTask(), task.getStatus().toString());
    }

}
