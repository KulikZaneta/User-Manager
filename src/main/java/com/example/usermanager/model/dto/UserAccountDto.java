package com.example.usermanager.model.dto;

import com.example.usermanager.model.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserAccountDto {
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must contain only letters and numbers")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=]).{8,}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character and no whitespace")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must contain only letters and numbers")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Lastname must contain only letters and numbers")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    private Gender gender;

    @Pattern(regexp = "^[0-9]+$", message = "Age must contain only digits")
    @Min(value = 0, message = "Age must be positive")
    private int age;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime accountCreationDate = LocalDateTime.now();
}

