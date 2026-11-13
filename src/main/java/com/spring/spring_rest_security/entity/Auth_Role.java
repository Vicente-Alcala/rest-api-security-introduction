package com.spring.spring_rest_security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * JPA entity representing an authorization role
 * mapped to the {@code auth_role} database table.
 */
@Entity 
@Table(name="auth_role")
public class Auth_Role {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    /**
     * Default no-argument constructor required by JPA.
     */
    public Auth_Role() {
    }
    
    public Auth_Role(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Auth_Role [id=" + id + ", name=" + name + "]";
    }
}
