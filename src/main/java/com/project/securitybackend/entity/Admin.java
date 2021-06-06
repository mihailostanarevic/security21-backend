package com.project.securitybackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends BaseEntity{

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
