package com.learningSpring.Security.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private String userName;
    private String userEmailId; 
    private String password;
    //private String matchingPassword;
    
    
}
