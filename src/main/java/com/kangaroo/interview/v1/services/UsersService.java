package com.kangaroo.interview.v1.services;

import com.kangaroo.interview.v1.requests.*;
import com.kangaroo.interview.v1.exceptions.users.UsersExceptionHandler;
import com.kangaroo.interview.v1.models.Users;
import com.kangaroo.interview.v1.repositories.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;


    private final Clock clock;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String error = "User not found";

    public List<Users> getAllUsers(Long page, Long size) {

        Pageable find = PageRequest.of(Math.toIntExact(page), Math.toIntExact(size));
        Page<Users> users = usersRepository.findByIsDeleted(false, find);

        return users.getContent();
    }

    public void deleteUser(DeleteUserRequest deleteUserRequest) throws UsersExceptionHandler {

        Optional<Users> find = usersRepository.findById(deleteUserRequest.getUserId());

        if(!find.isPresent()){
            throw new UsersExceptionHandler(this.error);
        }

        Users user = find.get();
        user.setIsDeleted(true);
        usersRepository.save(user);
    }

    public Users getParticularUser(Long id) throws UsersExceptionHandler {

        Optional <Users> user = usersRepository.findById(id);

        if(!user.isPresent()){
            throw new UsersExceptionHandler(this.error);
        }

        return user.get();
    }

    public Users updateUserDetails(UpdateUserDetailsRequest updateUserDetailsRequest) throws UsersExceptionHandler {
        /**
         * Encrypt Passwords
         */
        Optional <Users> find = usersRepository.findById(updateUserDetailsRequest.getUserId());

        if(!find.isPresent()){
            throw new UsersExceptionHandler(this.error);
        }

        Users user = find.get();

        if(updateUserDetailsRequest.getEmail() != null){
            user.setEmail(updateUserDetailsRequest.getEmail());
        }

        if(updateUserDetailsRequest.getPassword() != null){
            user.setPassword(passwordEncoder.encode(updateUserDetailsRequest.getPassword()));
        }

        if(updateUserDetailsRequest.getFirstName() != null){
            user.setFirstName(updateUserDetailsRequest.getFirstName());
        }

        if(updateUserDetailsRequest.getLastName() != null){
            user.setLastName(updateUserDetailsRequest.getLastName());
        }

        if(updateUserDetailsRequest.getPhoneNumber() != null){
            user.setPhoneNumber(updateUserDetailsRequest.getPhoneNumber());
        }

        if(updateUserDetailsRequest.getPhysicalAddress() != null){
            user.setPhysicalAddress(updateUserDetailsRequest.getPhysicalAddress());
        }

        user.setUpdatedAt(ZonedDateTime.now(clock));

        usersRepository.save(user);

        return user;
    }

    public void resetUserPassword(ResetUserPasswordRequest resetUserPasswordRequest) throws UsersExceptionHandler{

        Optional <Users> find = usersRepository.findById(resetUserPasswordRequest.getUserId());

        if(!find.isPresent()){
            throw new UsersExceptionHandler(this.error);
        }

        Users user = find.get();
        user.setPassword(passwordEncoder.encode(resetUserPasswordRequest.getPassword()));
        usersRepository.save(user);
    }
}
