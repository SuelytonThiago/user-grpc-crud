package com.example.rest.util;

import com.example.domain.entities.User;
import com.example.rest.dto.UserImputDto;
import com.example.rest.dto.UserOutputDto;

public class UserConvertUtil {

    public static UserOutputDto userToUserOutputDto(User user){
        return new UserOutputDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPass()
        );
    }

    public static User userImputDtoToUser(UserImputDto imputDto){
        return new User(null,
                imputDto.getName(),
                imputDto.getEmail(),
                imputDto.getPass());
    }


}
