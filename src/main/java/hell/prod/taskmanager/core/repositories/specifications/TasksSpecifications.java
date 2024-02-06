package hell.prod.taskmanager.core.repositories.specifications;

import hell.prod.taskmanager.core.entities.Task;
import org.springframework.data.jpa.domain.Specification;

public class TasksSpecifications {
    public static Specification<Task> nameLike(String namePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("user").get("name"), String.format("%%%s%%", namePart)));
    }

    public static Specification<Task> taskNameLike(String taskNamePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", taskNamePart)));
    }
}
