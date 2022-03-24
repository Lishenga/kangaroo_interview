package com.kangaroo.interview.v1.requests;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetParticularUserRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = -7751995429858919497L;
    
	@NotNull(message = "Provide userId")
	private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}