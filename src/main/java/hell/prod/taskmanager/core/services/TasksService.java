package hell.prod.taskmanager.core.services;

import hell.prod.taskmanager.core.converter.TaskConverter;
import hell.prod.taskmanager.core.entities.Task;
import hell.prod.taskmanager.core.entities.User;
import hell.prod.taskmanager.core.exeptions.ResourceNotFoundExeption;
import hell.prod.taskmanager.core.repositories.TasksRepository;
import hell.prod.taskmanager.core.repositories.specifications.TasksSpecifications;
import hell.prod.taskmanager.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;
    private final UsersService usersService;
    private final TaskConverter taskConverter;

    public Page<Task> findAll(String partName,String partTaskName, Integer page){
        Specification<Task> spec = Specification.where(null);
        if(partName != null){
            spec = spec.and(TasksSpecifications.nameLike(partName));
        }
        if(partTaskName != null){
            spec = spec.and(TasksSpecifications.taskNameLike(partTaskName));
        }
        return tasksRepository.findAll(spec, PageRequest.of(page-1,8));
    }
    public Optional<Task> findById(Long id){
        return tasksRepository.findById(id);
    }
    public Task save(Task task){
        return tasksRepository.save(task);
    }
    public void deleteById(Long id){
        tasksRepository.deleteById(id);
    }
    @Transactional
    public Optional<Task> updateUser(Long taskId, String userName){
       Optional<Task> taskUpdate = tasksRepository.findById(taskId.describeConstable().orElseThrow(() ->
               new ResourceNotFoundExeption("Невозможно обновление задания, не найдено в базе")));
       if (taskUpdate.isPresent()){
           User user = usersService.findByName(userName);
           taskUpdate.get().setUser(user);
       }
       return taskUpdate;
    }
    @Transactional
    public TaskDto updateTask(TaskDto taskDto){
        Optional<Task> taskUpdate = tasksRepository.findById(taskDto.getId().describeConstable().orElseThrow(() ->
                new ResourceNotFoundExeption("Невозможно обновление задания, не найдено в базе, задача: " + taskDto.getName())));
        if (taskUpdate.isPresent()){
            taskUpdate.get().setTask(taskDto.getTask());
            return taskConverter.entityToDto(taskUpdate.get());
        }
        return taskDto;
    }

    public Task createTask(Task task){
        tasksRepository.save(task);
        return task;
    }
    @Transactional
    public Task updateStatus(Task task){
        Optional<Task> taskUpdate = tasksRepository.findById(task.getId().describeConstable().orElseThrow(() ->
                new ResourceNotFoundExeption("Невозможно обновление задания, не найдено в базе, задача: " + task.getName())));
        if (taskUpdate.isPresent()){
            taskUpdate.get().setStatus(task.getStatus());
            return taskUpdate.get();
        }
        return task;
    }

}
