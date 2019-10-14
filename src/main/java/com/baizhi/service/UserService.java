package com.baizhi.service;

import com.baizhi.entity.userMap;

import java.util.List;

public interface UserService {
    public List<userMap> findAll();

    List<Integer> creat_date();
}
