package com.kangaroo.interview.v1.security;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.kangaroo.interview.v1.requests.RegisterUserRequest;
import com.kangaroo.interview.v1.requests.UserAuthenticationRequest;
import com.kangaroo.interview.v1.exceptions.security.SecurityExceptionHandler;
import com.kangaroo.interview.v1.models.Users;
import com.kangaroo.interview.v1.repositories.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    private final Clock clock;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String error = "Client not found";
    
    @Override
    public UserDetails loadUserByUsername(String identifier){
           
        Optional <Users> users = usersRepository.findByEmail(identifier);

        if(!users.isPresent()){
            throw new UsernameNotFoundException(identifier);
        }

        return new User(users.get().getEmail(), users.get().getPassword(),
                this.getGrantedAuthorities());
    }

    private List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        return grantedAuthorities;
    }

    public UserDetails userAuthentication(UserAuthenticationRequest userAuthenticateUserRequest) throws SecurityExceptionHandler {

        Optional <Users> users = usersRepository.findByEmail(userAuthenticateUserRequest.getEmail());

        if(!users.isPresent()){
            throw new SecurityExceptionHandler("User not Found");
        }

        boolean matches = passwordEncoder.matches(userAuthenticateUserRequest.getPassword(), users.get().getPassword());

        if (!matches) {
			throw new SecurityExceptionHandler("Invalid password");
		}
        
        return new User(users.get().getEmail(), users.get().getEmail(),
                new ArrayList<>());
    }

    public Users registerUser(RegisterUserRequest registerUserRequest) {
        registerUserRequest.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        Users users = new Users();
        users.setCreatedAt(ZonedDateTime.now(clock));
        users.setUpdatedAt(ZonedDateTime.now(clock));
        users.userCreate(registerUserRequest);
        usersRepository.save(users);

        return users;
    }
}