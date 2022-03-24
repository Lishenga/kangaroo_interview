package com.kangaroo.interview.v1.responses.users;

import java.io.Serializable;

import com.kangaroo.interview.v1.models.Users;

public class GeneralUsersResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 7849227330286243939L;

	private int status;

    private String message;

    private Users data;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Users getData() {
        return this.data;
    }

    public void setData(Users data) {
        this.data = data;
    }
}