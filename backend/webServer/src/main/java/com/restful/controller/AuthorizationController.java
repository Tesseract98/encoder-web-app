package com.restful.controller;

import com.restful.model.Privilege;
import com.restful.model.Role;
import com.restful.model.User;
import com.restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/authorization")
public class AuthorizationController {

    @Autowired
    public UserService userService;

    @Transactional
    public void addUser(User user) {
        List<Role> roles = Collections.singletonList(
                new Role(2L, "ROLE_ADMIN",
                        Collections.singletonList(new Privilege(1L, "CAN_ADD"))
                )
        );
        User user2 = User.builder()
                .id(77L)
                .email("new@we.com")
                .login("created")
                .password("created")
                .name("Иван")
                .surname("Иванов")
                .patronymic("Иванович")
                .roles(roles)
                .enabled(true)
                .build();
        user.setLogin("asd");
        user.setEmail("asdsda");
        userService.create(user2);
        userService.create(user);
    }

}
