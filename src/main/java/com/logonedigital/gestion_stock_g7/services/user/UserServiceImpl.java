package com.logonedigital.gestion_stock_g7.services.user;


import com.logonedigital.gestion_stock_g7.dto.user.UserDTO;
import com.logonedigital.gestion_stock_g7.dto.user.UserReqDTO;
import com.logonedigital.gestion_stock_g7.entities.Role;
import com.logonedigital.gestion_stock_g7.entities.RoleName;
import com.logonedigital.gestion_stock_g7.entities.User;
import com.logonedigital.gestion_stock_g7.exception.PasswordConfirmException;
import com.logonedigital.gestion_stock_g7.exception.ResourceExistException;
import com.logonedigital.gestion_stock_g7.exception.ResourceNotFoundException;
import com.logonedigital.gestion_stock_g7.mapper.UserMapper;
import com.logonedigital.gestion_stock_g7.repositories.RoleRepo;
import com.logonedigital.gestion_stock_g7.repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.Optional;


@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(UserReqDTO userReqDTO) {

        if(!userReqDTO.getPassword().equals(userReqDTO.getPasswordConfirm()))
            throw new PasswordConfirmException("Password aren't equal");

        Optional<User> userExist = this.userRepo.findByEmail(userReqDTO.getEmail());
        if(userExist.isPresent())
            throw new ResourceExistException("This user already exist !");

        User user = this.userMapper.fromUserReqDTO(userReqDTO);
        user.setPassword(this.passwordEncoder.encode(userReqDTO.getPassword()));
        user.setIsActivated(false);
        user.setCreatedAt(Instant.now());
        Role role = this.roleRepo
                .findByRoleName(RoleName.ROLE_EMPLOYE)
                .orElseThrow(()->new ResourceNotFoundException("Role not found !"));
        user.setRoles(Collections.singletonList(role));

        User user1 = this.userRepo.save(user);
        log.info("user {} :", user1);
        return this.userMapper.fromUser(user1);
    }
}
