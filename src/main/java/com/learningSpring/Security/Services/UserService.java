package com.learningSpring.Security.Services;

import java.util.Calendar;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learningSpring.Security.Entity.PassWordRestVerificationToken;
import com.learningSpring.Security.Entity.User;
import com.learningSpring.Security.Entity.VerificationToken;
import com.learningSpring.Security.Model.ResetPassWordModel;
import com.learningSpring.Security.Model.UserModel;
import com.learningSpring.Security.Repository.PassWordResetVerificationRepo;
import com.learningSpring.Security.Repository.UserRepo;
import com.learningSpring.Security.Repository.VerificationTokenRepo;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VerificationTokenRepo verificationTokenRepo;
    @Autowired
    private PassWordResetVerificationRepo passWordResetVerificationRepo;
    //@Autowired
    //private ModelMapper modelMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserModel userModel){
        //User user = modelMapper.map(userModel, User.class);
        User user = new User();
        user.setUserName(userModel.getUserName());
        user.setUserEmailId(userModel.getUserEmailId());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRole("User");
        

        userRepo.save(user);
        return user;
        
    
    } 

    public void saveVerificationtokenForUser(User user,String token){
        VerificationToken verificationToken=new VerificationToken(user, token);
        verificationTokenRepo.save(verificationToken);

    }

    public String validatingRegisteredUser(String token){
        VerificationToken verificationToken=null;
        for(VerificationToken i: verificationTokenRepo.findAll()){
            if(i.getVerificationToken().equals(token)){
                verificationToken=i;
                break;
            }
        }
        User user=verificationToken.getUser();
        if(verificationToken==null){
            return "invalid";
        }
        Calendar calendar=Calendar.getInstance();
        if((verificationToken.getExpireTime().getTime()-calendar.getTime().getTime())<=0){
            verificationTokenRepo.delete(verificationToken);
            return "invalid";
        }
        user.setEnabled(true);
        userRepo.save(user);
        return "valid";
    }
    public String resendVerificationToken( String oldToken){
        VerificationToken verificationToken=null;
        for(VerificationToken i:verificationTokenRepo.findAll()){
            if(i.getVerificationToken().equals(oldToken)){
                verificationToken=i;
                break;
            }
        }
        verificationToken.setVerificationToken(UUID.randomUUID().toString());
        return verificationToken.getVerificationToken();
    }

    public String createVerificationtoken(String userName) {
        User user=null;
        for(User u:userRepo.findAll()){
            if(u.getUserName().equals(userName)){
                user=u;
                break;
            }

        }
        String token=UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken(user,token);
        verificationTokenRepo.save(verificationToken);
        return token;
    }

    public String createPassWordRestToken(String userName) {
        User resetUser=null;
        PassWordRestVerificationToken pwvt=null;
        for(User user:userRepo.findAll()){
            if(user.getUserName().equals(userName)){
                resetUser=user;
                break;
            }
        }
        if(resetUser!=null){
            pwvt=new PassWordRestVerificationToken(resetUser);
            passWordResetVerificationRepo.save(pwvt);
            return pwvt.getPassWordRestVerificationTokenName();
        }

        return null;
    }

    public String resetPassWord(String token, ResetPassWordModel resetPassWordModel) {
        User user=null;
        for(PassWordRestVerificationToken i: passWordResetVerificationRepo.findAll()){
            if(i.getPassWordRestVerificationTokenName().equals(token)){
                user=i.getUser();
                user.setPassword(passwordEncoder.encode(resetPassWordModel.getNewPassWord()));
                userRepo.save(user);
                return "password has been changed";
            }
        }
    

        
        return "invalid token";
    }

    
  
}
