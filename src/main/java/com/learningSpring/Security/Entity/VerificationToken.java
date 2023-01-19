package com.learningSpring.Security.Entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
    private static final int duration=10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int verificationTokenId;
    private String verificationToken;
    private Date expireTime;

    @OneToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
        
    )
    @JoinColumn(name = "fk_verificationTokenId_userId"
        ,referencedColumnName = "userId"
        ,nullable = false

    )

    private User user;

    public VerificationToken(User user,String verificationToken){
        super();
        this.user=user;
        this.verificationToken=verificationToken;
        this.expireTime=calDate(duration);
    }
   /*  private Date calExpireTime(int durationInMinutes){
        Calendar calender= Calendar.getInstance();
        calender.setTimeInMillis(new Date().getTime());
        calender.add(Calendar.MINUTE, durationInMinutes);
        Date date = calender.getTime();
        return date;
    }*/

    //to work only with the token
    public VerificationToken(String verificationToken){
        super();
        this.verificationToken=verificationToken;
        this.expireTime=calDate(duration);
    }

    private Date calDate(int minutes){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    

    
}
