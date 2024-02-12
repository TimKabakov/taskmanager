package hell.prod.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Модель пользователя")
public class UserDto {
    @Schema(description = "ID пользователя", required = true, example = "1")
    private Long id;
    @Schema(description = "Имя пользователя", required = true, example = "Тимофей")
    private String name;
    @Schema(description = "Логин пользователя", required = true, example = "t.user")
    private String login;


}
