package com.aquafits.library.data.model.users;

import com.aquafits.library.data.model.books.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    private String id;

    private Book book;

    private Calendar start;

    private Calendar end;

    private boolean isReturned;
}
