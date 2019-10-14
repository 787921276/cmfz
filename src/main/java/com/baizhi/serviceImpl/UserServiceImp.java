package com.baizhi.serviceImpl;

import com.baizhi.entity.userMap;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<userMap> findAll() {
        List<userMap> all = userMapper.findAll();
        return all;
    }

    @Override
    public List<Integer> creat_date() {
        List<Integer> integers = userMapper.creat_date();
        return integers;
    }
}
