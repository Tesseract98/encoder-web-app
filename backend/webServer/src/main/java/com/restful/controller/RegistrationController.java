package com.restful.controller;

import com.restful.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {

    @PutMapping
    public HttpStatus addUser(@Valid @RequestBody UserDto userDto) {
        return HttpStatus.BAD_REQUEST;
    }

}
