package com.restful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull
    private Long id;

    @Size(min = 2, message = "Минимальная длина 2 символа")
    private String name;

    @Size(min = 2, message = "Минимальная длина 2 символа")
    private String surname;

    private String patronymic;

    @Email
    private String email;

    @Size(min = 5, message = "Минимальная длина 5 символов")
    private String login;

    @Size(min = 6, message = "Минимальная длина 6 символов")
    @Pattern(
            regexp = "(([a-zA-Z]*)([!-@])+([a-zA-Z]*))+",
            message = "должен содержать хотя бы одну цифру или некоторый специальный символ"
    )
    private String password;

}
