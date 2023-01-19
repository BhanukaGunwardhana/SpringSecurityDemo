package com.learningSpring.Security.Entity.Listener;

import java.util.UUID;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.learningSpring.Security.Entity.User;
import com.learningSpring.Security.Events.RegistrationCompletedEvent;
import com.learningSpring.Security.Services.UserService;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class RegistrationCompletedEventListener implements ApplicationListener<RegistrationCompletedEvent> {
    @Autowired
    private UserService userService;

    @Override

    public void onApplicationEvent(RegistrationCompletedEvent event) {
        
        //create verification token for the user with link
        User user=event.getUser();
        String token=UUID.randomUUID().toString();
        userService.saveVerificationtokenForUser(user, token);
        //send mail to user
        String url=event.getApplicationUrl()+"/verifyRegistration?token="+token;
// have to write sendVerificationEmail()
        log.info("click the url for verification"+ url); /*mocking */  

        
    }

}
