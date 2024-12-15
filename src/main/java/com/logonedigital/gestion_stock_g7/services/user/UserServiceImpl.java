package com.logonedigital.gestion_stock_g7.services.user;


import com.logonedigital.gestion_stock_g7.dto.user.ActivationCode;
import com.logonedigital.gestion_stock_g7.dto.user.UserDTO;
import com.logonedigital.gestion_stock_g7.dto.user.UserReqDTO;
import com.logonedigital.gestion_stock_g7.entities.Activation;
import com.logonedigital.gestion_stock_g7.entities.Role;
import com.logonedigital.gestion_stock_g7.entities.RoleName;
import com.logonedigital.gestion_stock_g7.entities.User;
import com.logonedigital.gestion_stock_g7.exception.AccountException;
import com.logonedigital.gestion_stock_g7.exception.PasswordConfirmException;
import com.logonedigital.gestion_stock_g7.exception.ResourceExistException;
import com.logonedigital.gestion_stock_g7.exception.ResourceNotFoundException;
import com.logonedigital.gestion_stock_g7.mapper.UserMapper;
import com.logonedigital.gestion_stock_g7.repositories.ActivationRepo;
import com.logonedigital.gestion_stock_g7.repositories.RoleRepo;
import com.logonedigital.gestion_stock_g7.repositories.UserRepo;
import com.logonedigital.gestion_stock_g7.services.emailService.EmailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;


@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private final ActivationRepo activationRepo;

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
        this.sendActivationCode(user1);
        return this.userMapper.fromUser(user1);
    }

    @Override
    public void sendActivationCode(User user) {
        Activation activation = new Activation();
        Instant createdAt = Instant.now();
        activation.setCreatedAt(createdAt);
        activation.setUser(user);
        activation.setExpiredAt(createdAt.plus(10, MINUTES));
        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        activation.setCode(String.format("%06d",randomInteger));
        this.emailSender.notify(activation);
        this.activationRepo.save(activation);
    }

    @Override
    public void activateAccount(ActivationCode activationCode) {
        Activation activation = this.searchActivationCode(activationCode.code());
        //verify if activationCode still available
        if(Instant.now().isAfter(activation.getExpiredAt()))
            throw new AccountException("Activation code expired! Generate new activation code");

        User user = this.userRepo
                .findById(activation.getUser().getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User not found !"));
        user.setIsActivated(true);
        user.setUpdatedAt(Instant.now());
        activation.setActivation(Instant.now());

        this.userRepo.saveAndFlush(user);
        this.activationRepo.saveAndFlush(activation);
    }

    @Override
    public Activation searchActivationCode(String code) {
        return this.activationRepo.findByCode(code)
                .orElseThrow(()->new ResourceNotFoundException("Activation code not found !"));
    }


}
