package com.restful.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

}
