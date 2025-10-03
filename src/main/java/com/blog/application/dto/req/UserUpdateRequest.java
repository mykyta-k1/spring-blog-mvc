package com.blog.application.dto.req;

import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateRequest {

    @NotNull
    private UUID id;

    @NotBlank(message = "Username field cannot be empty")
    @Size(min = 6, max = 32, message = "Username must be between 6 and 32 characters long")
    private String username;

    @NotBlank(message = "Email field cannot be empty")
    @Email(message = "Incorrect email format")
    @Size(max = 254, message = "Email must not exceed 254 characters")
    private String email;

    @NotBlank(message = "Password field cannot be empty")
    @Size(min = 8, max = 72, message = "Password length must be between 8 and 72 characters")
    @Pattern(
        regexp =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$",
        message =
            "Password is not strong enough, use uppercase and lowercase letters, numbers and special characters")
    private String password;

    @Past
    private OffsetDateTime createdAt;

    @PastOrPresent
    private OffsetDateTime updatedAt;
}
