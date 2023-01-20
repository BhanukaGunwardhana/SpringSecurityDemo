package com.learningSpring.Security.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPassWordModel {
    private String newPassWord;
    private String oldPassWord;
    
}
