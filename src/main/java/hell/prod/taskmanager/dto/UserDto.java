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

    public UserDto(Long id, String name, String login) {
        this.id = id;
        this.name = name;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
