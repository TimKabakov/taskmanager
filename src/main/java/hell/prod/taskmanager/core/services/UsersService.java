package hell.prod.taskmanager.core.services;


import hell.prod.taskmanager.core.entities.Role;
import hell.prod.taskmanager.core.entities.User;
import hell.prod.taskmanager.core.repositories.RolesRepository;
import hell.prod.taskmanager.core.repositories.UsersRepository;
import hell.prod.taskmanager.core.repositories.specifications.UsersSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private final RolesRepository rolesRepository;
    @Autowired
    private final UsersRepository usersRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
        User userFromDB = findByName(user.getUsername());
        if(userFromDB != null){
            throw new IllegalArgumentException("Can't create a user: " +user.getUsername() +" already exists");
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }
    public void deleteById(Long id){
        if (usersRepository.findById(id).isPresent()) {
            usersRepository.deleteById(id);
    }
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = usersRepository.findByNameEquals(name);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
