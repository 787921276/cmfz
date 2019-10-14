package com.baizhi.controller;


import com.baizhi.entity.Banner;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("Admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("querUsernameAndPwd")
    public Map<String, Object> login(String username, String password, String code, HttpSession session) {
        Map<String, Object> login = adminService.querUsernameAndPwd(username, password, code, session);
        return login;
    }

    @RequestMapping("/findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        Integer count = adminService.count();

        Integer total = count % rows == 0 ? count / rows : count / rows + 1;

        List<Banner> byPage = adminService.findByPage(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", byPage);
        map.put("records", count);
        map.put("page", page);
        map.put("total", total);
        return map;

    }

    @ResponseBody
    @RequestMapping("/edit")
    public String edit(Banner banner, String oper, String id) {

        if (oper.equals("add")) {
            String s = UUID.randomUUID().toString();
            banner.setId(s);
            adminService.save(banner);
            return s;
        } else if (oper.equals("del")) {
            String[] split = id.split(",");
            adminService.delete(split);
        } else {
            adminService.updata(banner);
        }
        return null;
    }

}
