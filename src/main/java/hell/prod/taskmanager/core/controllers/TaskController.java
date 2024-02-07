package hell.prod.taskmanager.core.controllers;

import hell.prod.taskmanager.core.entities.Task;
import hell.prod.taskmanager.core.exeptions.ResourceNotFoundExeption;
import hell.prod.taskmanager.core.services.TasksService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Задачи", description = "Методы работы с задачами")
public class TaskController {
    private final TasksService tasksService;
    @GetMapping
    public Page<Task> getAllTasks(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "part_name", required = false) String partName,
            @RequestParam(name = "part_task_name",required = false)String partTaskName
    ){
        if(page<1){
            page =1;
        }
        return tasksService.findAll(partName,partTaskName, page);
    }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable @Parameter(description = "Идентификатор Задачи", required = true) Long id){
        return tasksService.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Задача не найдена" + id));
    }
    @PutMapping
    public Task changeTaskUser(@RequestBody Task task){
        return tasksService.updateTask(task);
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id){
        tasksService.deleteById(id);
    }
}
