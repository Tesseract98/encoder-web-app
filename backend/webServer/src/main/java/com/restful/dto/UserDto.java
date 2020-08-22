package com.restful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Size(min = 2, message = "Минимальная длина 2 символа")
    private String name;

    @Size(min = 2, message = "Минимальная длина 2 символа")
    private String surname;

    @NotNull
    private String patronymic;

    @Size(min = 5, message = "Минимальная длина 5 символов")
    private String login;

    @Size(min = 6, message = "Минимальная длина 6 символов")
    @Pattern(regexp = "(([a-zA-Z]*)([!-@])+([a-zA-Z]*))+",
            message = "должен содержать хотя бы одну цифру или некоторый специальный символ")
    private String password;

}
