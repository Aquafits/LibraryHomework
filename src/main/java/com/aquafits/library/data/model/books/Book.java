package com.aquafits.library.data.model.books;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String id;

    private String name;

    private Category category;

    private String ebookType;

    private String ebookUrl;

    private String description;

    private BigDecimal penaltyPerDay;

}
