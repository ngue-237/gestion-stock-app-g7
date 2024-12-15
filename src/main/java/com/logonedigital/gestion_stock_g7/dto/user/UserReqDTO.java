package com.logonedigital.gestion_stock_g7.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserReqDTO {

    private String firstname;
    @NotEmpty(message = "lastname required")
    private String lastname;
    @Email
    private String email;
    @Length(min = 8, message = "min 8 characters")
    private String password;
    private String passwordConfirm;
}
