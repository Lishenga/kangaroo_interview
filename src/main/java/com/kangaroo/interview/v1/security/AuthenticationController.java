package com.kangaroo.interview.v1.security;

import com.kangaroo.interview.v1.requests.RegisterUserRequest;
import com.kangaroo.interview.v1.requests.UserAuthenticationRequest;
import com.kangaroo.interview.v1.responses.authentication.UserAuthenticationResponse;
import com.kangaroo.interview.v1.responses.users.RegisterUserResponse;
import com.kangaroo.interview.v1.utils.JwtUtil;
import com.kangaroo.interview.v1.exceptions.security.SecurityExceptionHandler;
import com.kangaroo.interview.v1.models.Users;
import com.kangaroo.interview.v1.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/1.0/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UsersRepository usersRepository;

	private UserAuthenticationResponse userAuthenticationResponse = new UserAuthenticationResponse();

	private RegisterUserResponse registerUserResponse = new RegisterUserResponse();

    @RequestMapping(value = "/authenticateuser", method = RequestMethod.POST)
	public ResponseEntity<UserAuthenticationResponse> authenticateUser(@RequestBody UserAuthenticationRequest userAuthenticateUserRequest) throws SecurityExceptionHandler  {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userAuthenticateUserRequest.getEmail(), userAuthenticateUserRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new SecurityExceptionHandler("Kindly provide the right credentials");
		}

		final UserDetails userDetails = authenticationService.userAuthentication(userAuthenticateUserRequest);

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		Optional<Users> user = usersRepository.findByEmail(userDetails.getUsername());

		if(!user.isPresent()){
			throw new SecurityExceptionHandler("User not found");
		}

		userAuthenticationResponse.setData(user.get());
		userAuthenticationResponse.setMessage("Success");
		userAuthenticationResponse.setStatus(200);
		userAuthenticationResponse.setToken(jwt);

		return ResponseEntity.ok(userAuthenticationResponse);
	}

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
		authenticationService.registerUser(registerUserRequest);
		
		registerUserResponse.setMessage("Success");
		registerUserResponse.setStatus(200);

		return ResponseEntity.ok(registerUserResponse);
	}
}