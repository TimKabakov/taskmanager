package hell.prod.taskmanager.core.repositories;

import hell.prod.taskmanager.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role,Long> {
}
