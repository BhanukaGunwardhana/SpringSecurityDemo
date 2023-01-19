package com.learningSpring.Security.Controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learningSpring.Security.Entity.User;
import com.learningSpring.Security.Entity.VerificationToken;
import com.learningSpring.Security.Events.RegistrationCompletedEvent;
import com.learningSpring.Security.Model.UserModel;
import com.learningSpring.Security.Repository.UserRepo;
import com.learningSpring.Security.Services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    //@RequestMapping("/userRegistration")
    //to insert user registration details
    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel,final HttpServletRequest httpServletRequest){
        User user = userService.registerUser(userModel);
        RegistrationCompletedEvent rcevent=new RegistrationCompletedEvent(user,makeApplicationUrl(httpServletRequest));
        applicationEventPublisher.publishEvent(rcevent);
        return "Success";
        


    }
    //to verify registered user by email
    @GetMapping("/verifyRegistration")
    public String validatingRegisteredUser(@RequestParam("token") String token){
        String out=userService.validatingRegisteredUser(token);
        if(out.equalsIgnoreCase("valid")){
            return "User registration succeeded "; 
        }
        else{return "Invalid user";}
    } 

    public String makeApplicationUrl(HttpServletRequest httpServletRequest){
        return "http://"+
                httpServletRequest.getServerName()+
                ":"+
                httpServletRequest.getServerPort()+
                httpServletRequest.getContextPath();

    }
    @GetMapping("/resendVerificationtoken")
    public String resendVerificationToken(@RequestParam("oldToken") String oldToken, HttpServletRequest httpServletRequest){
        String token=userService.resendVerificationToken(oldToken);
        String url=makeApplicationUrl(httpServletRequest)+"/verifyRegistration?token="+token;
        log.info("verify your registration :"+url);
        return "Another mail have been sent";
    }
    @GetMapping("/resendVerificationTokenWithUserName")
    public String resendVerificationTokenWithUserName(@RequestParam("userName") String userName, HttpServletRequest httpServletRequest){
        String token = userService.createVerificationtoken(userName);
        String url=makeApplicationUrl(httpServletRequest)+"/verifyRegistration?token="+token;
        log.info("verify your registration :"+url);
        return "Another mail have been sent";


    }
}
