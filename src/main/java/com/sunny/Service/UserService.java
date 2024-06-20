package com.sunny.Service;


import com.sunny.Exception.PasswordInvalidException;
import com.sunny.Exception.UserAlreadyPresentException;
import com.sunny.Exception.UserDoesPresentInException;
import com.sunny.Model.User;

public interface UserService {
    User signUp(String name ,String email , String password) throws UserAlreadyPresentException;

    User login(String email , String password) throws UserDoesPresentInException, PasswordInvalidException;
}
