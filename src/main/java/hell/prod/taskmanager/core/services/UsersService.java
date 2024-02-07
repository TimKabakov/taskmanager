package hell.prod.taskmanager.core.services;


import hell.prod.taskmanager.core.entities.User;
import hell.prod.taskmanager.core.repositories.UsersRepository;
import hell.prod.taskmanager.core.repositories.specifications.UsersSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public Page<User> findAll(String namePart, Integer page){
        Specification<User> spec =  Specification.where(null);
        if(namePart != null){
            spec = spec.and(UsersSpecification.nameLike(namePart));
        }
        return usersRepository.findAll(spec, PageRequest.of(page-1,8));
    }
    public Optional<User> findById(Long id){
        return usersRepository.findById(id);
    }
    public User findByName(String name){
        return usersRepository.findByNameEquals(name);
    }
    public User save(User user){
        return usersRepository.save(user);
    }
    public void deleteById(Long id){
        usersRepository.deleteById(id);
    }
}
