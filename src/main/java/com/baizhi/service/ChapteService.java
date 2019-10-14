package com.baizhi.service;

import com.baizhi.entity.chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapteService {
    //分页
    public List<chapter> findByPage(@Param("page") Integer page, @Param("rows") Integer rows, @Param("albumid") String albumid);

    //条数
    public Integer count(String albumid);

    //添加
    public String save(chapter chapter);

    //修改
    public void updata(chapter chapter);

    //删除
    public void delete(String[] id);

}
