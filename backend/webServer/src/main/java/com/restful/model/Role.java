package com.restful.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_fk", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_fk", referencedColumnName = "id")
    )
    List<Privilege> privileges;

}
