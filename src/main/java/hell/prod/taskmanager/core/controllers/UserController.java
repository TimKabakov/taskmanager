package hell.prod.taskmanager.core.controllers;

import hell.prod.taskmanager.core.converter.UserConverter;
import hell.prod.taskmanager.core.entities.User;
import hell.prod.taskmanager.core.exeptions.ResourceNotFoundExeption;
import hell.prod.taskmanager.core.services.UsersService;
import hell.prod.taskmanager.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Метод работы с пользователями")
public class UserController {
    private final UsersService usersService;
    private final UserConverter userConverter;

    @Operation(
            summary = "Запрос на получение списка пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping
    public Page<UserDto> getAllUsers(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                     @RequestParam(name = "part_name", required = false) String partName){
        if(page<1){
            page = 1;
        }
        return usersService.findAll(partName,page).map(userConverter::entityToDto);
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable @Parameter(description = "Идентификатор пользователя", required = true) Long id){
        return userConverter.entityToDto(usersService.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Задача не найдена" + id)));
    }
    @PostMapping
    public UserDto createNewUser(@RequestBody User user){
        return userConverter.entityToDto(usersService.save(user));
    }
}
