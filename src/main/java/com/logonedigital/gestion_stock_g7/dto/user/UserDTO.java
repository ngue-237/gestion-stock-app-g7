package com.logonedigital.gestion_stock_g7.dto.user;

import lombok.Data;

@Data
public class UserDTO {
    private Integer userId;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean isActivated;
}
