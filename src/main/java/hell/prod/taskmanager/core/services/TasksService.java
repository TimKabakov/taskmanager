package hell.prod.taskmanager.core.services;

import hell.prod.taskmanager.core.entities.Task;
import hell.prod.taskmanager.core.exeptions.ResourceNotFoundExeption;
import hell.prod.taskmanager.core.repositories.TasksRepository;
import hell.prod.taskmanager.core.repositories.specifications.TasksSpecifications;
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
    public Task update(Task task){
       Optional<Task> taskUpdate = tasksRepository.findById(task.getId().describeConstable().orElseThrow(() -> new ResourceNotFoundExeption("Невозможно обновление продукта, не найден в базе, id: " + task.getId())));
       if (taskUpdate.isPresent()){
           taskUpdate.get().setUser(task.getUser());
           taskUpdate.get().setTask(task.getTask());
           return taskUpdate.get();
       }
       return task;
    }
}
