package com.darwish.springcloud.service.impl;

import com.darwish.springcloud.dto.UserDto;
import com.darwish.springcloud.entity.User;
import com.darwish.springcloud.exception.EmailAlreadyExistException;
import com.darwish.springcloud.exception.ResourceNotFoundException;
import com.darwish.springcloud.mapper.UserMapper;
import com.darwish.springcloud.repository.UserRepository;
import com.darwish.springcloud.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistException(String.format("User with email: %s already exist", user.getEmail()));
        }

        User savedUser = userRepository.saveAndFlush(user);

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        UserDto userDto = UserMapper.mapToUserDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> all = userRepository.findAll();
        return all.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);

        User existUser = userRepository.findById(user.getId()).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", user.getId())
        );
        existUser.setFirstName(userDto.getFirstName());
        existUser.setLastName(userDto.getLastName());
        existUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.saveAndFlush(existUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );
        userRepository.deleteById(id);
    }
}
