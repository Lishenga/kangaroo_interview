package com.kangaroo.interview.v1.responses.authentication;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3866875349074342632L;
    
    private final String jwt;
}