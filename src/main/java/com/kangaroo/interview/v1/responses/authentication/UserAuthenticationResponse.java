package com.kangaroo.interview.v1.responses.authentication;

import java.io.Serializable;

import com.kangaroo.interview.v1.models.Users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAuthenticationResponse implements Serializable  {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1257528136267882194L;

	private int status;

    private String message;

    private String token;

    private Users data;
}