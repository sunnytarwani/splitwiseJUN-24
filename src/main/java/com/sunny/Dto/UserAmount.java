package com.sunny.Dto;

import com.sunny.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserAmount {

    private User user;
    private double amount;

}
