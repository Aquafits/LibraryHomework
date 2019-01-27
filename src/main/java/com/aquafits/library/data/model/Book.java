package com.aquafits.library.data.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 32)
    private String id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Category category;

    private String eBookType;

    private String eBookUrl;

    private String description;

    private BigDecimal penaltyPerDay;

}
