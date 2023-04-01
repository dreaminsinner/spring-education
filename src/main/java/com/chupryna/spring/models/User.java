package com.chupryna.spring.models;

import com.chupryna.spring.services.RoleService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ID")
    private long id;

    @Column(unique = true, nullable = false, name = "USERNAME")
    @NotEmpty(message = "Login can not be empty")
    private String username;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "not valid email")
    @Column(unique = true, nullable = false, name = "EMAIL")
    private String email;

    @NotEmpty(message = "First name can not be empty")
    @Size(min = 2, max = 30, message = "Wrong login length")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotEmpty(message = "Last name can not be empty")
    @Size(min = 2, max = 30, message = "Wrong login length")
    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

}
