package com.logonedigital.gestion_stock_g7.services.user;

import com.logonedigital.gestion_stock_g7.dto.user.ActivationCode;
import com.logonedigital.gestion_stock_g7.dto.user.UserDTO;
import com.logonedigital.gestion_stock_g7.dto.user.UserReqDTO;
import com.logonedigital.gestion_stock_g7.entities.Activation;
import com.logonedigital.gestion_stock_g7.entities.User;

public interface UserService {
    UserDTO registerUser(UserReqDTO userReqDTO);
    void sendActivationCode(User user);
    void activateAccount(ActivationCode activationCode);
    Activation searchActivationCode(String code);
}
