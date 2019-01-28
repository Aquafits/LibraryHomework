package com.aquafits.library.data.model.strategies;

import com.aquafits.library.data.model.books.Book;
import com.aquafits.library.data.model.users.Contract;
import com.aquafits.library.exceptions.BorrowException;
import com.aquafits.library.exceptions.ReturnException;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class GenericStrategy extends BorrowStrategy {

    public GenericStrategy(String id, String name, int maxItems, int maxBorrowDays, List<Contract> contracts) {
        super(id, name, maxItems, maxBorrowDays, contracts);
    }

    @Override
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

    @Override
    public boolean borrowBook(Book book) throws BorrowException {
        if (getRemainingBorrowTimes() <= 0) {
            throw new BorrowException("Reached max borrow items");
        }

        Calendar now = Calendar.getInstance();
        Calendar toReturn = Calendar.getInstance();
        toReturn.add(Calendar.DATE, maxBorrowDays);
        contracts.add(new Contract(null, book, now, toReturn, false));

        return true;
    }

    @Override
    public boolean returnBook(Book book) throws ReturnException {
        return false;
    }


    private int getRemainingBorrowTimes() {
        int nowBorrows = 0;
        for (Contract c : contracts) {
            if (!c.isReturned()) {
                nowBorrows++;
            }
        }
        return maxItems - nowBorrows;
    }
}
