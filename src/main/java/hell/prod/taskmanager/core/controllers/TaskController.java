package hell.prod.taskmanager.core.controllers;

import hell.prod.taskmanager.core.converter.TaskConverter;
import hell.prod.taskmanager.core.entities.Task;
import hell.prod.taskmanager.core.exeptions.ResourceNotFoundExeption;
import hell.prod.taskmanager.core.services.TasksService;
import hell.prod.taskmanager.core.services.UsersService;
import hell.prod.taskmanager.core.utils.TaskStatus;
import hell.prod.taskmanager.dto.TaskDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Задачи", description = "Методы работы с задачами")
public class TaskController {
    private final TasksService tasksService;
    private final TaskConverter taskConverter;
    private final UsersService usersService;
    @Operation(
            summary = "Запрос на получение списка задач",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping
    public Page<TaskDto> getAllTasks(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "part_name", required = false) String partName,
            @RequestParam(name = "part_task_name",required = false)String partTaskName
    ){
        if(page<1){
            page =1;
        }
        return tasksService.findAll(partName,partTaskName, page).map(taskConverter::entityToDto);
    }
    @Operation(
            summary = "Запрос на получение задачи по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable @Parameter(description = "Идентификатор Задачи", required = true) Long id){
        return taskConverter.entityToDto(tasksService.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Задача не найдена" + id)));
    }
    @PostMapping
    public TaskDto createNewTask(@RequestBody TaskDto taskDto){
        Task task = new Task(taskDto.getId(),taskDto.getName(), taskDto.getTask(),
                usersService.findByName(taskDto.getName()), TaskStatus.fromString(taskDto.getStatus()));
        tasksService.createTask(task);
        return taskConverter.entityToDto(task);
    }
    @PutMapping
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return tasksService.updateTask(taskDto);
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id){
        tasksService.deleteById(id);
    }
}
