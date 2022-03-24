package com.kangaroo.interview.v1.responses;

import java.io.Serializable;

public class GeneralResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -5594460069152098073L;

	private int status;

    private String message;

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
}