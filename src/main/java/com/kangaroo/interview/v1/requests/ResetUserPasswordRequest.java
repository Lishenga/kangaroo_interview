package com.kangaroo.interview.v1.requests;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ResetUserPasswordRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 461590970010043143L;

	@NotNull(message = "Provide userId")
    private Long userId;
    
    @NotNull(message = "Provide password")
	private String password;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}