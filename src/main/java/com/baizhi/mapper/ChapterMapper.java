package com.baizhi.mapper;

import com.baizhi.entity.chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    //分页
    public List<chapter> findByPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("albumid") String albumid);

    //条数
    public Integer count(String albumid);

    //添加
    public void save(chapter chapter);

    //修改
    public void updata(chapter chapter);

    //删除
    public void delete(String[] id);
}
