package com.sunny.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserLoginResponseDto {
    private Long id;
    private String name;
    private String email;
    private List<UserFriendResponseDto> friendList;
    private List<GroupResponseDto> groups;
}
