package com.aquafits.library.app;

import com.aquafits.library.business.BookService;
import com.aquafits.library.data.model.Book;
import com.aquafits.library.utils.CategoryFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private Gson gson = new Gson();

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 新增书籍
     * form-data
     * @param name
     * @param categoryName
     * @param eBookType
     * @param eBookUrl
     * @param description
     * @param penaltyPerDay
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object addBook(@RequestParam(value = "name") String name,
                          @RequestParam(value = "category") String categoryName,
                          @RequestParam(value = "eBookType") String eBookType,
                          @RequestParam(value = "eBookUrl") String eBookUrl,
                          @RequestParam(value = "description") String description,
                          @RequestParam(value = "penaltyPerDay") String penaltyPerDay) {
        Map<String, String> map = new HashMap<>();

        Book book = new Book();
        book.setName(name);
        book.setCategory(CategoryFactory.getInstance().getCategory(categoryName));
        book.setEBookType(eBookType);
        book.setEBookUrl(eBookUrl);
        book.setDescription(description);
        book.setPenaltyPerDay(BigDecimal.valueOf(Double.valueOf(penaltyPerDay)));
        bookService.saveBook(book);

        map.put("data", gson.toJson(book));
        map.put("success", "true");

        return map;
    }

    /**
     * 编辑书
     *
     * @param book
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object saveBook(@PathVariable("id") String id,
                           @RequestBody Book book) {
        Map<String, String> map = new HashMap<>();

        bookService.saveBook(book);

        map.put("data", gson.toJson(book));
        map.put("success", "true");

        return map;
    }

    /**
     * 删除书
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteBook(@PathVariable("id") String id) {
        Map<String, String> map = new HashMap<>();

        boolean isDeleted = bookService.deleteBook(id);

        map.put("data", "");
        map.put("success", "" + isDeleted);

        return map;
    }

    /**
     * 找到所有书籍
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object findAll() {
        Map<String, String> map = new HashMap<>();

        List<Book> books = bookService.findAll();

        map.put("data", gson.toJson(books));
        map.put("success", "true");

        return map;
    }

    /**
     * 找到一个分类下的所有书籍
     *
     * @param categoryName
     * @return
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public Object findBookByCategoryName(@RequestParam("category") String categoryName) {
        Map<String, String> map = new HashMap<>();

        List<Book> books = bookService.findBookByCategoryName(categoryName);

        map.put("data", gson.toJson(books));
        map.put("success", "true");

        return map;
    }

    /**
     * 找到特定id书籍
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findBookById(@PathVariable("id") String id) {
        Map<String, String> map = new HashMap<>();

        Book book = bookService.findBookById(id);

        map.put("data", gson.toJson(book));
        map.put("success", "true");

        return map;
    }


    /**
     * 找到特定名字书籍
     *
     * @param bookName
     * @return
     */
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public Object findBookByName(@RequestParam(value = "name") String bookName) {
        Map<String, String> map = new HashMap<>();

        Book book = bookService.findBookByName(bookName);

        map.put("data", gson.toJson(book));
        map.put("success", "true");

        return map;
    }
}
