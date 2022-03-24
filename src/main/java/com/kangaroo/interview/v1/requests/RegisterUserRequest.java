package com.kangaroo.interview.v1.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RegisterUserRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1374069780361978918L;

    @NotNull(message = "Email address must be provided")
    private String email;

    @NotNull(message = "First Name must be provided")
    private String firstName;

    @NotNull(message = "Last Name must be provided")
    private String lastName;

    @NotNull(message = "Phone Number must be provided")
    private String phoneNumber;

    @NotNull(message = "Physical Address must be provided")
    private String physicalAddress;

    @NotNull(message = "Password must be provided")
    private String password;
}
