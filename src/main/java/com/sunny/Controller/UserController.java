package com.sunny.Controller;


import com.sunny.Dto.UserLoginRequestDto;
import com.sunny.Dto.UserRegistrationRequestDto;
import com.sunny.Exception.PasswordInvalidException;
import com.sunny.Exception.UserAlreadyPresentException;
import com.sunny.Exception.UserDoesPresentInException;
import com.sunny.Exception.UserRegistrationInvalidDataException;
import com.sunny.Mapper.EntityDTOMapper;
import com.sunny.Model.User;
import com.sunny.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/splitwise")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserRegistrationRequestDto requestDto) throws UserRegistrationInvalidDataException, UserAlreadyPresentException {
        validateUserRequest(requestDto);
        User savedUser = userService.signUp(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        return ResponseEntity.ok(EntityDTOMapper.toDTO(savedUser));
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDto requestDto) throws UserRegistrationInvalidDataException, UserDoesPresentInException, PasswordInvalidException {
        validateLoginUserRequest(requestDto);
        User savedUser = userService.login(requestDto.getEmail(), requestDto.getPassword());
        return ResponseEntity.ok(EntityDTOMapper.toDTO(savedUser));
    }

    private void validateUserRequest(UserRegistrationRequestDto requestDto) throws UserRegistrationInvalidDataException {
        if(requestDto.getName() == null || requestDto.getEmail()  == null|| requestDto.getPassword() == null){
            throw new UserRegistrationInvalidDataException("Details provided are Invalid, kindly complete the signup details");
        }
    }

    public void validateLoginUserRequest(UserLoginRequestDto userLoginRequestDto) throws UserRegistrationInvalidDataException {
        if(userLoginRequestDto.getEmail() == null || userLoginRequestDto.getPassword() == null){
            throw new UserRegistrationInvalidDataException("Invalid Credentials");
        }
    }
}
