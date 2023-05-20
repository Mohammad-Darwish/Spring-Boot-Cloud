package com.darwish.springcloud.service.impl;

import com.darwish.springcloud.dto.UserDto;
import com.darwish.springcloud.entity.User;
import com.darwish.springcloud.repository.UserRepository;
import com.darwish.springcloud.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
//        User user = UserMapper.mapToUser(userDto);
        User user = mapper.map(userDto, User.class);

        User savedUser = userRepository.saveAndFlush(user);

//        return UserMapper.mapToUserDto(savedUser);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

//        UserDto userDto = UserMapper.mapToUserDto(optionalUser.get());
        UserDto userDto = mapper.map(optionalUser.get(), UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> all = userRepository.findAll();

//        return all.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        return all.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
//        User user = UserMapper.mapToUser(userDto);
        User user = mapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findById(user.getId());
        User updatedUser;
        if (!optionalUser.isEmpty()) {
            User existUser = optionalUser.get();
            existUser.setFirstName(userDto.getFirstName());
            existUser.setLastName(userDto.getLastName());
            existUser.setEmail(userDto.getEmail());
            updatedUser = userRepository.saveAndFlush(existUser);
        } else {
            log.info("The user doesn't exist, so updating failed");
            updatedUser = optionalUser.get();
        }
//        return UserMapper.mapToUserDto(updatedUser);
        return mapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
