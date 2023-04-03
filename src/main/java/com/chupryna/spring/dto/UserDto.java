package com.chupryna.spring.dto;

import com.chupryna.spring.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@Component
@NoArgsConstructor
public class UserDto {

    private long id;

    @NotEmpty(message = "Login can not be empty")
    private String username;

    private String password;

    private String passwordConfirmation;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "not valid email")
    private String email;

    @NotEmpty(message = "First name can not be empty")
    @Size(min = 2, max = 30, message = "Wrong login length")
    private String firstName;

    @NotEmpty(message = "Last name can not be empty")
    @Size(min = 2, max = 30, message = "Wrong login length")
    private String lastName;

    private Date birthday;

    private Role role;

    private String age;

    public UserDto(
            long id,
            String username,
            String password,
            String email,
            String firstName,
            String lastName,
            Date birthday,
            Role role
    ) {
        this.age = String.valueOf(Period.between(birthday.toLocalDate(), LocalDate.now()).getYears());
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role = role;
    }
}
