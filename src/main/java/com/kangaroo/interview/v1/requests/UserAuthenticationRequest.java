package com.kangaroo.interview.v1.requests;

import javax.validation.constraints.NotNull;

public class UserAuthenticationRequest {

    @NotNull(message = "Email address must be provided")
    private String email;

    @NotNull(message = "Password must be provided")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
