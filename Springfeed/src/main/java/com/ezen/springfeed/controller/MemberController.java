package com.ezen.springfeed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    // TODO : implement
    @RequestMapping("/login")
    public String login() {
        return "";
    }

    // TODO : implement
    @RequestMapping("/loginForm")
    public String loginForm() {
        return "";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        return "main";
    }

    // TODO : implement
    @RequestMapping("/join")
    public String join() {
        return "";
    }

    // TODO : implement
    @RequestMapping("/joinForm")
    public String joinForm() {
        return "";
    }

    // TODO : implement
    @RequestMapping("/follow")
    public String follow() {
        return "";
    }

    // TODO : implement
    @RequestMapping("/unfollow")
    public String unfollow() {
        return "";
    }
}
