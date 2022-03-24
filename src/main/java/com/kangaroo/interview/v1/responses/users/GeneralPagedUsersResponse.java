package com.kangaroo.interview.v1.responses.users;

import java.io.Serializable;
import java.util.List;

import com.kangaroo.interview.v1.models.Users;

public class GeneralPagedUsersResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 3905761224628163163L;

	private int status;

    private String message;

    private List<Users> data;

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

    public List<Users> getData() {
        return this.data;
    }

    public void setData(List<Users> data) {
        this.data = data;
    }
}