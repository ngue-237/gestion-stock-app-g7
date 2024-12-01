package com.logonedigital.gestion_stock_g7.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerReqDTO {
    @Length(min = 3, max = 50, message = "firstname must have min 3 caracters and max 50 caracters")
    @NotEmpty(message = "This field must be fill")
   private String firstname;
   private String lastname;
   @Email(message = "Your email address isn't correct")
   private String email;
   private String phone;
   private LocationReqDTO locationReqDTO;
}
