package com.example.rest.services;


import com.example.rest.dto.UserImputDto;
import com.example.rest.dto.UserOutputDto;

import java.util.List;

public interface UserService {
    UserOutputDto create (UserImputDto imputDto);
    UserOutputDto findById (String id);
    void delete(String id);
    List<UserOutputDto> findAll();

}
