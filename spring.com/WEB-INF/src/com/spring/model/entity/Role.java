package com.spring.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
    @Table(name = "project.role")
    public class Role implements Serializable {
        @Id
        @NotNull
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private long id;

        public  long getId() {
            return id;
        }

        @NotNull
        @Column(name = "NAME")
        private String role;

        public  String getRole() {
            return role;
        }

        public  void setRole(String role) {
            this.role = role;
        }
}
