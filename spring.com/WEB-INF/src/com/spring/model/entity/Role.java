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

        public synchronized void setId(long id) {
            this.id = id;
        }

        public synchronized long getId() {
            return id;
        }

        @NotNull
        @Column(name = "NAME")
        private String role;

        public synchronized String getRole() {
            return role;
        }

        public synchronized void setRole(String role) {
            this.role = role;
        }
}
