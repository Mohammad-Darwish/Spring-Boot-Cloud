package com.darwish.springcloud.mapper;

import com.darwish.springcloud.dto.UserDto;
import com.darwish.springcloud.entity.User;

public class UserMapper {

    // convert user dto to jpa user
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail()
        );
    }

    // convert jpa user to user dto
    public static User mapToUser(UserDto userDto) {
        return new User(
            userDto.getId(),
            userDto.getFirstName(),
            userDto.getLastName(),
            userDto.getEmail()
        );
    }


}
