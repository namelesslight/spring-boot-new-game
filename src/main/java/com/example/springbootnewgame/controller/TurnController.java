package com.example.springbootnewgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TurnController {
    @RequestMapping("/goLogin")
    public String gotoLogin() {
        return "/login";
    }

    @RequestMapping("/goRegister")
    public String gotoRegister() {
        return "/register";
    }

    @RequestMapping("/goGame")
    public String gotoGame() {
        return "/game";
    }

    @RequestMapping("/goLevelList")
    public String gotoLevelList() {
        return "/level-list";
    }

    @RequestMapping("/goSpeedList")
    public String gotoSpeedList() {
        return "/speed-list";
    }
}
