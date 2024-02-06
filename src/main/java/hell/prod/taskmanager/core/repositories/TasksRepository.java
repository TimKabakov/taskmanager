package hell.prod.taskmanager.core.repositories;

import hell.prod.taskmanager.core.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
}
