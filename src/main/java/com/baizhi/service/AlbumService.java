package com.baizhi.service;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumService {
    //添加
    public void save(Album album);

    //修改
    public void updata(Album album);

    //删除
    public void delete(String[] id);

    //分页
    public List<Album> findByPage(@Param("start") Integer page, @Param("rows") Integer rows);

    //条数
    public Integer count();

    //修改图片路径
    public void updatePath(String id, String cover);
}
