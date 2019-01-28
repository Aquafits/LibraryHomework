package com.aquafits.library.data;

import com.aquafits.library.data.model.books.Book;
import com.aquafits.library.data.model.books.Category;
import com.aquafits.library.data.model.strategies.BorrowStrategy;
import com.aquafits.library.data.model.strategies.GenericStrategy;
import com.aquafits.library.data.model.users.Contract;
import com.aquafits.library.data.model.users.Role;
import com.aquafits.library.data.model.users.User;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Mock {
    public List<Role> roles;
    public List<Category> categories;
    public List<Book> books;
    public List<Contract> contracts;
    public List<BorrowStrategy> strategies;
    public List<User> users;

    private static Mock ourInstance = new Mock();

    public static Mock getInstance() {
        return ourInstance;
    }

    private Mock() {
        try {
            //set roles
            Role adminRole = new Role("1", "Admin");
            Role teacherRole = new Role("2", "Teacher");
            Role studentRole = new Role("3", "Student");
            Role genericRole = new Role("4", "Generic");
            roles = new ArrayList<>(Arrays.asList(adminRole, studentRole, teacherRole, genericRole));

            //set categories
            Category interestingCategory = new Category("1", "interesting");
            Category boringCategory = new Category("1", "boring");
            Category teacherOnlyCategory = new Category("3", "teacher_only");
            categories = new ArrayList<>(Arrays.asList(interestingCategory, boringCategory, teacherOnlyCategory));


            //set books
            Book interestingBook = new Book("1", "Jokes", interestingCategory, "epub",
                    "/storage/Jokes.epub", "Best Jokes", new BigDecimal(1.0));
            Book boringBook = new Book("2", "Math", boringCategory, "pdf",
                    "/storage/Math.pdf", "College Math", new BigDecimal(1.5));
            Book teacherOnlyBook = new Book("3", "Teaching", teacherOnlyCategory, "word",
                    "/storage/Teaching.docx", "College Teaching", new BigDecimal(2.0));
            books = new ArrayList<>(Arrays.asList(interestingBook, boringBook, teacherOnlyBook));

            //set contracts
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            start.setTime(format.parse("2018-09-13 08:25:23"));
            end.setTime(format.parse("2018-10-13 23:59:59"));
            Contract studentContract1 = new Contract("1", interestingBook, start, end, false);

            start.setTime(format.parse("2018-05-12 12:25:23"));
            end.setTime(format.parse("2018-06-12 23:59:59"));
            Contract teacherContract1 = new Contract("2", boringBook, start, end, false);
            Contract teacherContract2 = new Contract("3", teacherOnlyBook, start, end, true);

            contracts = new ArrayList<>(Arrays.asList(studentContract1, teacherContract1, teacherContract2));
            List<Contract> studentContracts = new ArrayList<>(Arrays.asList(studentContract1));
            List<Contract> teacherContracts = new ArrayList<>(Arrays.asList(teacherContract1, teacherContract2));

            //set borrow strategies
            BorrowStrategy studentStrategy = new GenericStrategy("1", "Student strategy",
                    10, 30, new ArrayList<>());
            studentStrategy.setContracts(studentContracts);
            BorrowStrategy teacherStrategy = new GenericStrategy("2", "Teacher strategy",
                    40, 60, new ArrayList<>());
            teacherStrategy.setContracts(teacherContracts);
            BorrowStrategy defaultStrategy = new GenericStrategy("3", "Default strategy",
                    0, 0, new ArrayList<>());
            strategies = new ArrayList<>(Arrays.asList(studentStrategy, teacherStrategy, defaultStrategy));

            //set users
            User adminUser = new User("1", "admin@lib.com", "admin", defaultStrategy,
                    null, "admin", "18724008366", adminRole);
            User teacherUser = new User("2", "teacher@lib.com", "teacher", teacherStrategy,
                    "Computer Science", "Mike", "18724008366", teacherRole);
            User studentUser = new User("3", "student@lib.com", "student", studentStrategy,
                    "Computer Science", "Helen", "18724008366", studentRole);
            users = new ArrayList<>(Arrays.asList(adminUser, teacherUser, studentUser));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
