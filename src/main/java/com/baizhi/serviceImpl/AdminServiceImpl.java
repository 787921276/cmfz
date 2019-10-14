package com.baizhi.serviceImpl;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> querUsernameAndPwd(String username, String password, String code, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String code1 = (String) session.getAttribute("validationCodes");
        if (code1.equals(code)) {
            Admin admin = adminMapper.querUsernameAndPwd(username);
            if (admin != null) {
                if (password.equals(admin.getPassword())) {
                    map.put("mag", "ok");
                    return map;
                } else {
                    map.put("mag", "密码不正确");
                    return map;
                }
            } else {
                map.put("mag", "此用户不存在");
                return map;
            }
        } else {
            map.put("mag", "验证码错误");
            return map;
        }
    }

    @Override
    public List<Banner> findAll(HttpServletResponse response) {
        return adminMapper.findAll();
    }

    @Override
    public void save(Banner banner) {
        adminMapper.save(banner);
    }

    @Override
    public void updata(Banner banner) {
        adminMapper.updata(banner);
    }

    @Override
    public void delete(String[] id) {
        adminMapper.delete(id);
    }

    @Override
    public List<Banner> findByPage(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        List<Banner> byPage = adminMapper.findByPage(start, rows);
        return byPage;
    }

    @Override
    public Integer count() {
        Integer count = adminMapper.count();
        return count;
    }

    @Override
    public void updatePath(String id, String img_path) {
        adminMapper.updatePath(id, img_path);
    }
}
