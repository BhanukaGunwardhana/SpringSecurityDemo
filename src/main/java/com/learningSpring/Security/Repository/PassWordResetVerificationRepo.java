package com.learningSpring.Security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningSpring.Security.Entity.PassWordRestVerificationToken;
@Repository
public interface PassWordResetVerificationRepo extends JpaRepository<PassWordRestVerificationToken,Integer>{
    
}
