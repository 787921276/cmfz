package com.baizhi.mapper;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AdminMapper {
    //登陆
    public Admin querUsernameAndPwd(String username);

    //查询
    public List<Banner> findAll();

    //添加
    public void save(Banner banner);

    //修改
    public void updata(Banner banner);

    //删除
    public void delete(String[] id);

    //分页
    public List<Banner> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    //条数
    public Integer count();

    //修改图片路径
    public void updatePath(String id, String img_path);
}
