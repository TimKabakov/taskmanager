package hell.prod.taskmanager.core.converter;

import hell.prod.taskmanager.core.entities.User;
import hell.prod.taskmanager.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserConverter {

    public UserDto entityToDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getLogin());
    }
}
