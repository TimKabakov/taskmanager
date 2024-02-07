package hell.prod.taskmanager.core.repositories;

import hell.prod.taskmanager.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByNameEquals(String name);
}
