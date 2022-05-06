package com.epam.PasswordManagementSys.web.dto;

import com.epam.PasswordManagementSys.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "User Name Can't Be Empty!!")
    @Size(min = 5, max = 20, message = "Name should be of min 5 chars and max 20 chars")
    private String userName;
    @NotBlank(message = "User Name Can't Be Empty!!")
    @Size(min = 5, max = 20, message = "Name should be of min 5 chars and max 20 chars")
    @Pattern(regexp = Constants.PASSWORD_REGEX, message = "Password Should Contain Lower case , Upper case , Numbers and Special chars with min of 8 chars")
    private String password;
}
