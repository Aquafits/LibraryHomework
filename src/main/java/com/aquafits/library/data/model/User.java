package com.aquafits.library.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 32)
    private String id;

    private String email;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contract> contracts;

    @OneToOne(fetch = FetchType.LAZY)
    private BorrowStrategy strategy;

    private String department;

    private String nickname;

    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private Role role;

    private int getRemainingBorrowTimes(){
        int nowBorrows = 0;
        for(Contract c:contracts){
            if(!c.getIsReturned()){
                nowBorrows ++;
            }
        }
        return strategy.getMaxItems() - nowBorrows;
    }

    public boolean isBorrowable(){
        if(getRemainingBorrowTimes()<=0){
            return false;
        }
        return true;
    }

    public BigDecimal getPenalty() {
        Calendar now = Calendar.getInstance();
        BigDecimal penalty = new BigDecimal(0);
        for (Contract c : contracts) {
            if (now.after(c.getEnd())) {
                BigDecimal ppd = c.getBook().getPenaltyPerDay();
                int interval_days = (int) ((now.getTimeInMillis() - c.getEnd().getTimeInMillis())
                        / (1000 * 60 * 60 * 24));
                penalty = penalty.add(ppd.multiply(new BigDecimal(interval_days)));
            }
        }
        return penalty;
    }
}