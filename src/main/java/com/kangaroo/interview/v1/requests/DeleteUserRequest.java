package com.kangaroo.interview.v1.requests;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DeleteUserRequest implements Serializable {

    private static final long serialVersionUID = -2482466998852437395L;

    @NotNull(message = "Provide userId")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
