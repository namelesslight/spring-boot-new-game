package com.example.springbootnewgame.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springbootnewgame.pojo.GameUser;
import com.example.springbootnewgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


@RestController
@RequestMapping("/")
public class GameController {
    @Autowired
    GameService gameService;

    @PutMapping("userRegister")
    public Integer register(@RequestParam("user_name") String userName,
                            @RequestParam("user_password") String userPassword) {
        return gameService.register(userName, userPassword);
    }

    @GetMapping("userLogin")
    public Integer login(@RequestParam("user_name") String userName,
                         @RequestParam("user_password") String userPassword, HttpServletResponse response) {
        response.addCookie(new Cookie("user_name", URLEncoder.encode(userName)));
        return gameService.login(userName, userPassword);
    }

    @GetMapping("getUserByLevel")
    public IPage<GameUser> getUserByLevel(@RequestParam("current_page") Integer currentPage,
                                          @RequestParam("page_size") Integer pageSize) {
        return gameService.getUserByLevel(currentPage, pageSize);
    }

    @GetMapping("getUserByAverage")
    public IPage<GameUser> getUserByAverage(@RequestParam("current_page") Integer currentPage,
                                            @RequestParam("page_size") Integer pageSize) {
        return gameService.getUserByAverage(currentPage, pageSize);
    }

    @GetMapping("getOneUser")
    public GameUser getOneUser(@RequestParam("user_name") String userName) {
        return gameService.getOneUser(userName);
    }

    @PatchMapping("updatePassword")
    public Integer updatePassword(@RequestParam("user_name") String userName,
                                  @RequestParam("user_password") String userPassword) {
        return gameService.updatePassword(userName, userPassword);
    }

    @PatchMapping("setBestLevel")
    public Integer setBestLevel(@RequestParam("user_name") String useName,
                                @RequestParam("best_level") Integer bestLevel) {
        return gameService.setBestLevel(useName, bestLevel);
    }

    @GetMapping("getBestLevel")
    public Integer getBestLevel(@RequestParam("user_name") String userName) {
        return gameService.getBestLevel(userName);
    }

    @PatchMapping("setBestAverage")
    public Integer setBestAverage(@RequestParam("user_name") String useName,
                                  @RequestParam("best_average") Double bestAverage) {
        return gameService.setBestAverage(useName, bestAverage);
    }

    @GetMapping("getBestAverage")
    public Double getBestAverage(@RequestParam("user_name") String userName) {
        return gameService.getBestAverage(userName);
    }

}
