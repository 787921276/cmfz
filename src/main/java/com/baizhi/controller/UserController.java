package com.baizhi.controller;

import com.baizhi.entity.userMap;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("echarts")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("queryMap")
    public List<userMap> querty() {
        List<userMap> all = userService.findAll();
        return all;
    }

    @RequestMapping("query")
    public List<Integer> creat() {
        List<Integer> integers = userService.creat_date();
        return integers;
    }
}
