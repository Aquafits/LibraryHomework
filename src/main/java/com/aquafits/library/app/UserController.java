package com.aquafits.library.app;

import com.aquafits.library.business.UserService;
import com.aquafits.library.data.model.users.User;
import com.aquafits.library.utils.factory.RoleFactory;
import com.aquafits.library.utils.factory.StrategyFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private Gson gson = new Gson();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 非管理员登录
     * 需要form-data
     *
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "/auth/user", method = RequestMethod.POST)
    public Object userAuth(@RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password) {

        Map<String, String> map = new HashMap<>();
        try {
            map.put("data", "");
            map.put("success", "" + userService.authUser(email, password));
        } catch (AuthException e) {
            map.put("data", e.getMessage());
            map.put("success", "false");
        }

        return map;
    }

    /**
     * 管理员登录
     * 需要form-data
     *
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "/auth/admin", method = RequestMethod.POST)
    public Object adminAuth(@RequestParam(value = "email") String email,
                            @RequestParam(value = "password") String password) {

        Map<String, String> map = new HashMap<>();
        try {
            map.put("data", "");
            map.put("success", "" + userService.authAdmin(email, password));
        } catch (AuthException e) {
            map.put("data", e.getMessage());
            map.put("success", "false");
        }

        return map;
    }

    /**
     * 注册
     * 需要form-data
     *
     * @param email
     * @param password
     * @param role
     * @param strategy
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object register(@RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "role") String role,
                           @RequestParam(value = "strategy") String strategy) {
        Map<String, String> map = new HashMap<>();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(RoleFactory.getInstance().getRole(role));
        user.setStrategy(StrategyFactory.getInstance().getStrategy(strategy));
        userService.saveUser(user);

        map.put("data", gson.toJson(user));
        map.put("success", "true");

        return map;

    }

    /**
     * 更新用户信息
     *
     * @param id
     * @param password
     * @param department
     * @param nickname
     * @param phoneNumber
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Object register(@PathVariable("id") String id,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "department") String department,
                           @RequestParam(value = "nickname") String nickname,
                           @RequestParam(value = "phone_number") String phoneNumber) {
        Map<String, String> map = new HashMap<>();

        boolean updated = userService.updateUser(id, password, department, nickname, phoneNumber);

        map.put("data", "");
        map.put("success", "" + updated);

        return map;

    }

    /**
     * 删除一个用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteUser(@PathVariable("id") String id) {
        Map<String, String> map = new HashMap<>();

        boolean isDeleted = userService.deleteUser(id);
        map.put("data", "");
        map.put("success", "" + isDeleted);


        return map;

    }

    /**
     * 找到所有用户
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object findAll() {
        Map<String, String> map = new HashMap<>();

        List<User> users = userService.findAll();
        map.put("data", gson.toJson(users));
        map.put("success", "true");

        return map;
    }

    /**
     * 找到特定id用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findUserById(@PathVariable("id") String id) {
        Map<String, String> map = new HashMap<>();

        User user = userService.findUserById(id);
        map.put("data", gson.toJson(user));
        map.put("success", "true");

        return map;
    }

    /**
     * 找到特定email用户
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public Object findUserByEmail(@RequestParam(value = "email") String email) {
        Map<String, String> map = new HashMap<>();

        User user = userService.findUserByEmail(email);
        map.put("data", gson.toJson(user));
        map.put("success", "true");

        return map;
    }

    /**
     * 借书
     * 需要form-data
     *
     * @param id
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/{id}/borrow", method = RequestMethod.POST)
    public Object borrowBook(@PathVariable("id") String id,
                             @RequestParam(value = "bookId") String bookId) {
        Map<String, String> map = new HashMap<>();

        boolean isBorrowed = userService.borrowBook(id, bookId);

        map.put("data", "");
        map.put("success", "" + isBorrowed);

        return map;
    }


    /**
     * 获得罚款信息
     *
     * @return
     */
    @RequestMapping(value = "/{id}/penalty", method = RequestMethod.GET)
    public Object getPenalty(@PathVariable("id") String id) {
        Map<String, String> map = new HashMap<>();

        BigDecimal penalty = userService.getPenalty(id);

        map.put("data", "" + penalty);
        map.put("success", "true");

        return map;
    }


}
