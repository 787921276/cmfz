package com.baizhi.mapper;

import com.baizhi.entity.userMap;

import java.util.List;

public interface UserMapper {
    public List<userMap> findAll();

    public List<Integer> creat_date();
}
