package com.aquafits.library.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 32)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    private Book book;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Calendar start;

    @Temporal(TemporalType.DATE)
    private Calendar end;

    private Boolean isReturned;
}
