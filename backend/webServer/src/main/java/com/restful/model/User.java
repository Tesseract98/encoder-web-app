package com.restful.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@Table(
        name = "users",
        indexes = {@Index(columnList = "login", name = "login_idx")}
)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "login", "password"})
@ToString(of = {"id", "name", "surname", "patronymic", "login"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    private String name;

    private String surname;

    private String patronymic;

    @Column(columnDefinition = "bit default true", nullable = false)
    private Boolean enabled;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_fk", referencedColumnName = "id")
    )
    List<Role> roles;

}
