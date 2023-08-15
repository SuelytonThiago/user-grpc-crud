package com.example.rest.services.impl;

import com.example.domain.repositories.UserRepository;
import com.example.rest.dto.UserImputDto;
import com.example.rest.dto.UserOutputDto;
import com.example.rest.services.UserService;
import com.example.rest.services.exceptions.AlreadyExistsException;
import com.example.rest.services.exceptions.ObjectNotFoundException;
import com.example.rest.util.UserConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserOutputDto create(UserImputDto imputDto) {
        this.userRepository.findByEmail(imputDto.getEmail()).ifPresent(e ->{
            throw new AlreadyExistsException(imputDto.getEmail());
        });
        var user = UserConvertUtil.userImputDtoToUser(imputDto);
        var userCreated = userRepository.save(user);
        return UserConvertUtil.userToUserOutputDto(userCreated);
    }

    @Override
    public UserOutputDto findById(String id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
       return UserConvertUtil.userToUserOutputDto(user);
    }

    @Override
    public void delete(String id) {
        var user = userRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException(id));
        userRepository.delete(user);
    }

    @Override
    public List<UserOutputDto> findAll() {
        return userRepository.findAll().stream().map( user ->{
            return UserConvertUtil.userToUserOutputDto(user);
        }).collect(Collectors.toList());
    }

}
