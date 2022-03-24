package com.kangaroo.interview.v1.models;

import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.kangaroo.interview.v1.requests.RegisterUserRequest;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", unique = false)
    private String firstName;

    @Column(name = "last_name", unique = false)
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number", nullable = true, unique = true)
    private String phoneNumber;

    @Column(name = "physical_address", unique = false)
    private String physicalAddress;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "is_logged_in", nullable = true)
    private Boolean isLoggedIn;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "deleted_at", nullable = true)
    private ZonedDateTime deletedAt;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    private String accessToken;
    
    public Users(final Users user) {
        this.id = user.id;
        this.email = user.email;
        this.password = user.password;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.phoneNumber = user.phoneNumber;
        this.isLoggedIn = user.isLoggedIn;
        this.createdAt = user.createdAt;
        this.updatedAt = user.updatedAt;
        this.deletedAt = user.deletedAt;
        this.isDeleted = user.isDeleted;
        this.accessToken = user.accessToken;
    }

    public void userCreate(RegisterUserRequest registerUserRequest) {
        this.email = registerUserRequest.getEmail();
        this.password = registerUserRequest.getPassword();
        this.firstName = registerUserRequest.getFirstName();
        this.lastName = registerUserRequest.getLastName();
        this.phoneNumber = registerUserRequest.getPhoneNumber();
        this.physicalAddress = registerUserRequest.getPhysicalAddress();
        this.isLoggedIn = false;
        this.isDeleted = false;
    }
}
