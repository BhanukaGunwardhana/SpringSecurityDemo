package com.learningSpring.Security.Entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassWordRestVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passWordRestVerificationTokenId;
    private String passWordRestVerificationTokenName;

    @OneToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(
        name = "passWordRestVerificationTokenId_userId",
        referencedColumnName = "userId" 
    )
    private User user;

    public PassWordRestVerificationToken(User user){
        super();
        this.user=user;
        this.passWordRestVerificationTokenName=UUID.randomUUID().toString();

    }

}
