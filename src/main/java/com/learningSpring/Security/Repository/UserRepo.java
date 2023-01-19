package com.learningSpring.Security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningSpring.Security.Entity.User;
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    
    
}
