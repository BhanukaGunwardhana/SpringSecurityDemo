package com.learningSpring.Security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningSpring.Security.Entity.VerificationToken;
@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Integer> {

    void findByVerificationToken(String oldeToken);
    
}
