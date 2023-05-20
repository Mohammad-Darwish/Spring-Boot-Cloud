package com.darwish.springcloud.mapper;

import com.darwish.springcloud.dto.UserDto;
import com.darwish.springcloud.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto maptoUserDto(User user);

    User mapToUser(UserDto userDto);
}
