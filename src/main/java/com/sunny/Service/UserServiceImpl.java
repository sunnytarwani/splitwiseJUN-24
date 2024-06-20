package com.sunny.Service;

import com.sunny.Exception.PasswordInvalidException;
import com.sunny.Exception.UserAlreadyPresentException;
import com.sunny.Exception.UserDoesPresentInException;
import com.sunny.Model.User;
import com.sunny.Respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User signUp(String name, String email, String password) throws UserAlreadyPresentException {
        if(userRepository.findUserByEmail(email) != null){
            throw new UserAlreadyPresentException("Email " + email + " is already registered with us");
        }

        //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        //user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) throws UserDoesPresentInException, PasswordInvalidException {
        User savedUser = userRepository.findUserByEmail(email);
        //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if(savedUser == null){
            throw new UserDoesPresentInException("User is not present in our database");
        }

//        if(bCryptPasswordEncoder.matches(password, savedUser.getPassword())){
//            return savedUser;
//        }
//        else{
//            throw new PasswordInvalidException("Password Invalid");
//        }


        return savedUser;
    }
}
