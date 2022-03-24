package com.kangaroo.interview.v1.requests;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class UpdateUserDetailsRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 664729482738514778L;
    
    @NotNull(message = "Provide userId")
    private Long userId;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String password;

    private String physicalAddress;  
}