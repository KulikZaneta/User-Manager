package com.example.usermanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Users_Account")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_account_id")
    private Long id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    @NotNull
    private String username;

    @NotBlank
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @NotNull
    private String password;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Min(value = 0, message = "Age must be positive")
    private int age;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @NotNull
    @Column(name = "account_creation_date")
    private LocalDateTime accountCreationDate;
}
