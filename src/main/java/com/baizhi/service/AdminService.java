package com.baizhi.service;


import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface AdminService {
    //管理员登陆
    public Map<String, Object> querUsernameAndPwd(String username, String password, String code, HttpSession session);

    //查询
    public List<Banner> findAll(HttpServletResponse response);

    //添加
    public void save(Banner banner);

    //修改
    public void updata(Banner banner);

    //删除
    public void delete(String[] id);

    //分页
    public List<Banner> findByPage(@Param("start") Integer page, @Param("rows") Integer rows);

    //条数
    public Integer count();

    //修改图片路径
    public void updatePath(String id, String img_path);
}
