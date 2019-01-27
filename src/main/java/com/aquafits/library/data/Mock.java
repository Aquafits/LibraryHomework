package com.aquafits.library.data;

import com.aquafits.library.data.model.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                    "/storage/Teaching.docx", "College Teaching", new BigDecimal(1.8));
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
            BorrowStrategy studentStrategy = new BorrowStrategy("1", "Student strategy", 10, 30);
            BorrowStrategy teacherStrategy = new BorrowStrategy("2", "Teacher strategy", 20, 40);
            BorrowStrategy genericStrategy = new BorrowStrategy("3", "Generic strategy", 0, 0);
            strategies = new ArrayList<>(Arrays.asList(studentStrategy, teacherStrategy, genericStrategy));

            //set users
            User adminUser = new User("1", "admin@lib.com", "admin", null, null,
                    null, "admin", "18724008366", adminRole);
            User teacherUser = new User("2", "teacher@lib.com", "teacher", teacherContracts, teacherStrategy,
                    "Computer Science", "admin", "18724008366", adminRole);
            User studentUser = new User("3", "admin@lib.com", "admin", studentContracts, studentStrategy,
                    "Computer Science", "admin", "18724008366", adminRole);
            users = new ArrayList<>(Arrays.asList(adminUser, teacherUser, studentUser));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
