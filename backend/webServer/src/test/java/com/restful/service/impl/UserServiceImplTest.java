package com.restful.service.impl;

import com.restful.exceptions.service.NoSuchUserException;
import com.restful.model.Privilege;
import com.restful.model.Role;
import com.restful.model.User;
import com.restful.service.UserService;
import com.restful.service.config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImplTest extends DBUnitConfig {

    @Value("${dbunit.path-to-users}")
    private String xmlUserPath;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String[] ignoreCols = {
            "id", "password", "created_date", "last_modified_date", "enabled"
    };

    @Before
    @Override
    public void setUp() throws Exception {
        dataSet = getIDataSetFromXml(xmlUserPath + "init.xml");
        super.setUp();
    }

    private void checkTable(IDataSet expectedData, String tableName, boolean useIgnoreCols) throws Exception {
        ITable expectedTable = expectedData.getTable(tableName);
        ITable actualTable = getConnection().createTable(tableName);

        if (useIgnoreCols) {
            Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignoreCols);
        } else {
            Assertion.assertEquals(expectedTable, actualTable);
        }
    }

    @Test
    public void create() throws Exception {
        List<Role> roles = Collections.singletonList(
                new Role(2L, "ROLE_ADMIN",
                        Collections.singletonList(new Privilege(1L, "CAN_ADD"))
                )
        );
        User user = User.builder()
                .id(77L)
                .email("new@google.com")
                .login("created")
                .password("created")
                .name("Иван")
                .surname("Иванов")
                .patronymic("Иванович")
                .roles(roles)
                .enabled(true)
                .build();

        userService.create(user);

        IDataSet expectedData = getIDataSetFromXml(xmlUserPath + "created.xml");
        checkTable(expectedData, "users", true);
    }

    @Test
    public void update() throws Exception {
        User user = generateUser();
        user.setLogin("new_login");
        userService.update(user);

        IDataSet expectedData = getIDataSetFromXml(xmlUserPath + "updated.xml");
        checkTable(expectedData, "users", true);
        checkTable(expectedData, "users_roles", false);
    }

    @Test
    public void delete() throws Exception {
        User user = new User();
        user.setId(56L);
        userService.delete(user);

        IDataSet expectedData = getIDataSetFromXml(xmlUserPath + "deleted.xml");
        checkTable(expectedData, "users", true);
        checkTable(expectedData, "users_roles", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void getByIdException() {
        userService.getById(45L);
    }

    @Test
    public void getById() {
        User user = generateUser();
        User userFromDB = userService.getById(user.getId());
        assertTrue(passwordEncoder.matches(user.getPassword(), userFromDB.getPassword()));
    }

    @Test
    public void getByLogin() {
        User user = generateUser();
        User userFromDB = userService.getByLogin(user.getLogin());
        assertTrue(passwordEncoder.matches(user.getPassword(), userFromDB.getPassword()));
        assertEquals(user.getEmail(), userFromDB.getEmail());
        assertEquals(user.getLogin(), userFromDB.getLogin());
        assertEquals(user.getEnabled(), userFromDB.getEnabled());
        assertEquals(user.getPatronymic(), userFromDB.getPatronymic());
        assertEquals(user.getName(), userFromDB.getName());
        assertEquals(user.getSurname(), userFromDB.getSurname());
    }

    @Test
    public void getAll() {
        assertEquals(2, userService.getAll().size());
    }

    private User generateUser() {
        List<Role> roles = new LinkedList<>();
        roles.add(new Role(1L, "ROLE_USER", null));
        roles.add(new Role(2L, "ROLE_ADMIN", null));
        return User.builder()
                .id(55L)
                .email("admin@google.com")
                .login("admin")
                .password("password")
                .name("Вася")
                .surname("Василенко")
                .patronymic("Васильевич")
                .roles(roles)
                .enabled(true)
                .build();
    }

}
