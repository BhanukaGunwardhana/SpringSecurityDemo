package com.learningSpring.Security.Events;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationEvent;

import com.learningSpring.Security.Entity.User;

import lombok.Data;


@Data
public class RegistrationCompletedEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;

    
    public RegistrationCompletedEvent(User user,String applicationUrlString) {
       
        super(user);
        this.user=user;
        this.applicationUrl=applicationUrlString;
        //TODO Auto-generated constructor stub
    }
    
    
    
    
    
}
