package com.blog.application.user.service;

import com.blog.application.entity.UserEntity;
import com.blog.application.user.IO.CreateUserRequest;
import com.blog.application.user.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepo userRepo;

    public CreateUserRequest RegisterUser(CreateUserRequest userRequest){

        return convertToUserRequest( userRepo.save(convetToUserEntity(userRequest)));

    }



    public CreateUserRequest getUserDetails(String email){


       UserEntity user = userRepo.findByEmail(email)
               .orElseThrow(()-> new RuntimeException("User Not found"));
       return convertToUserRequest(user);

    }




    private UserEntity convetToUserEntity(CreateUserRequest createUserRequest){

        UserEntity user = new UserEntity();
        user.setEmail(createUserRequest.getEmail());
        user.setName(createUserRequest.getName());
        user.setPassword(createUserRequest.getPassword());
        user.setRole(createUserRequest.getRole());

        return user;

    }


    public CreateUserRequest updateUser(CreateUserRequest userRequest){
        UserEntity user = userRepo.findByEmail(userRequest.getEmail())
                .orElseThrow(()-> new RuntimeException(" User not exists"));

        user.setPassword(userRequest.getPassword());
        user.setName(userRequest.getName());
        userRepo.save(user);

        return convertToUserRequest(user);


    }


    @Transactional
    public void deleteUser(String email){
      UserEntity user = userRepo.findByEmail(email).orElseThrow(()->  new RuntimeException("user not found"));

      userRepo.delete(user);

//        userRepo.deleteAll((Iterable<? extends UserEntity>) user);
    }


    private CreateUserRequest convertToUserRequest(UserEntity user){
        CreateUserRequest user1 = new CreateUserRequest();
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        user1.setRole(user.getRole());
        return user1;
    }

    public List<UserEntity> findByRole(String role){
        return   userRepo.findByRole(role);
    }
}
