package hell.prod.taskmanager.core.repositories.specifications;

import hell.prod.taskmanager.core.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UsersSpecification {
    public static Specification<User> nameLike(String namePart){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart)));
    }
}
