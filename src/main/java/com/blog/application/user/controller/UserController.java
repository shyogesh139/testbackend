package com.blog.application.user.controller;

import com.blog.application.user.IO.ApiResponse;
import com.blog.application.user.IO.CreateUserRequest;
import com.blog.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;


    @GetMapping("/hii")
    public String hii(){
        return "hii from backend";
    }

    @GetMapping("getpasskey")
    public String helloUser(){
        return "61d7a750b41d45f8bf1ac113116e963a";
    }

    @GetMapping()
    public ResponseEntity<ApiResponse> getUserDeatils(@RequestParam String email){

//        System.out.println(getUserDeatils.getEmail());
        ApiResponse apiResponse = new ApiResponse(
                "User Fetched Sucessfully",
                userService.getUserDetails(email)

        );

      return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest createUserRequest){
//        System.out.println(createUserRequest);
        ApiResponse apiResponse = new ApiResponse(

                "User Created Sucessfully",
                userService.RegisterUser(createUserRequest)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateUser(@RequestBody CreateUserRequest createUserRequest){
        ApiResponse apiResponse = new ApiResponse(
                "User Updated Sucessfully",
                userService.updateUser(createUserRequest)
        );

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }





    @DeleteMapping
    public ResponseEntity<?> deleteUser(
            @RequestParam String email
    ){
        userService.deleteUser(email);
        return new ResponseEntity<>("User Deleted sucessfully ",HttpStatus.OK);
    }

    @GetMapping("role")
    public ResponseEntity<ApiResponse> findByRole(@RequestParam String role){
        ApiResponse apiResponse = new ApiResponse(
                "USER FETCHED SUCESSFULLY",
                userService.findByRole(role)
        );

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
