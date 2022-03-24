package com.kangaroo.interview.v1.controllers;

import com.kangaroo.interview.v1.requests.DeleteUserRequest;
import com.kangaroo.interview.v1.requests.ResetUserPasswordRequest;
import com.kangaroo.interview.v1.requests.UpdateUserDetailsRequest;
import com.kangaroo.interview.v1.responses.GeneralResponse;
import com.kangaroo.interview.v1.responses.users.GeneralPagedUsersResponse;
import com.kangaroo.interview.v1.responses.users.GeneralUsersResponse;
import com.kangaroo.interview.v1.services.UsersService;
import com.kangaroo.interview.v1.exceptions.users.UsersExceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {

    @Autowired
    private UsersService usersService;

    private String success = "Success";

    private GeneralUsersResponse generalUsersResponse = new GeneralUsersResponse();

    private GeneralPagedUsersResponse generalPagedUsersResponse = new GeneralPagedUsersResponse();

    private GeneralResponse generalResponse = new GeneralResponse();

    @GetMapping("/getparticularuser/{id}")
    public GeneralUsersResponse getParticularUser(@PathVariable long id) throws UsersExceptionHandler {

        var user = usersService.getParticularUser(id);
        generalUsersResponse.setData(user);
        generalUsersResponse.setMessage(this.success);
        generalUsersResponse.setStatus(200);
        return generalUsersResponse;
    }

    @GetMapping("/getallusers/{page}/{size}")
    public GeneralPagedUsersResponse getAllUsers(@PathVariable long page, @PathVariable Long size){

        var users = usersService.getAllUsers(page, size);
        generalPagedUsersResponse.setData(users);
        generalPagedUsersResponse.setMessage(success);
        generalPagedUsersResponse.setStatus(200);
        return generalPagedUsersResponse;
    }

    @RequestMapping(value = "deleteuser", method = RequestMethod.DELETE)
    public GeneralResponse deleteUser(@RequestBody DeleteUserRequest deleteUserRequest) throws UsersExceptionHandler {
        usersService.deleteUser(deleteUserRequest);
        generalResponse.setMessage(success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "updateuserdetails", method = RequestMethod.PUT)
    public GeneralUsersResponse updateUserDetails(@RequestBody UpdateUserDetailsRequest updateUserDetailsRequest) throws UsersExceptionHandler{

        var user = usersService.updateUserDetails(updateUserDetailsRequest);
        generalUsersResponse.setData(user);
        generalUsersResponse.setMessage(this.success);
        generalUsersResponse.setStatus(200);
        return generalUsersResponse;
    }

    @RequestMapping(value = "resetuserpassword", method = RequestMethod.POST)
    public GeneralResponse resetUserPassword(@RequestBody ResetUserPasswordRequest resetUserPasswordRequest) throws UsersExceptionHandler{

        usersService.resetUserPassword(resetUserPasswordRequest);
        generalResponse.setMessage(this.success);
        generalResponse.setStatus(200);
        return generalResponse;
    }
}


