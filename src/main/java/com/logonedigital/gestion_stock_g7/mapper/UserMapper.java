package com.logonedigital.gestion_stock_g7.mapper;

import com.logonedigital.gestion_stock_g7.dto.user.UserDTO;
import com.logonedigital.gestion_stock_g7.dto.user.UserReqDTO;
import com.logonedigital.gestion_stock_g7.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromUserReqDTO(UserReqDTO userReqDTO);
    UserDTO fromUser(User user);
}
