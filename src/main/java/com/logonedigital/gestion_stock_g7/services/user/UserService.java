package com.logonedigital.gestion_stock_g7.services.user;

import com.logonedigital.gestion_stock_g7.dto.user.UserDTO;
import com.logonedigital.gestion_stock_g7.dto.user.UserReqDTO;

public interface UserService {
    UserDTO registerUser(UserReqDTO userReqDTO);
}
