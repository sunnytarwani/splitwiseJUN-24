package com.sunny.Mapper;

import com.sunny.Dto.GroupResponseDto;
import com.sunny.Dto.UserFriendResponseDto;
import com.sunny.Dto.UserLoginResponseDto;
import com.sunny.Model.Group;
import com.sunny.Model.User;

import java.util.ArrayList;
import java.util.List;

public class EntityDTOMapper {

    public static UserLoginResponseDto toDTO(User user){
        UserLoginResponseDto responseDto = new UserLoginResponseDto();

        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setEmail(user.getEmail());

        //Conversion of user friends to friendlist
        List<UserFriendResponseDto> friednList = new ArrayList<>();
        if(user.getFriends()!=null){
            for(User friend: user.getFriends()){
                friednList.add(toFriendDTO(friend));
            }
            responseDto.setFriendList(friednList);
        }

        if(user.getGroups() != null){
            List<GroupResponseDto> groups = new ArrayList<>();
            for(Group group : user.getGroups()){
                groups.add(toDTOgroup(group));
            }

            responseDto.setGroups(groups);
        }

        return responseDto;
    }

    private static GroupResponseDto toDTOgroup(Group group) {
       return null;
    }

    private static UserFriendResponseDto toFriendDTO(User friend) {

        UserFriendResponseDto responseDto = new UserFriendResponseDto();
        responseDto.setId(Math.toIntExact(friend.getId()));
        responseDto.setName(friend.getName());

        return responseDto;
    }
}
