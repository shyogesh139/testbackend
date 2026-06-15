package com.blog.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "user_table")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @NotBlank(message = "id is required")
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "Role must be important")
    private String role;

    public UserEntity(@NotNull String email, @NotNull String name, @NotNull String password) {
        this.email = email;
        this.password = password;
        this.name = name;
    }


}
