package com.baizhi.serviceImpl;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void save(Album album) {
        albumMapper.save(album);
    }

    @Override
    public void updata(Album album) {
        albumMapper.updata(album);
    }

    @Override
    public void delete(String[] id) {
        albumMapper.delete(id);
    }

    @Override
    public List<Album> findByPage(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        List<Album> byPage = albumMapper.findByPage(start, rows);
        return byPage;
    }

    @Override
    public Integer count() {
        Integer count = albumMapper.count();
        return count;
    }

    @Override
    public void updatePath(String id, String cover) {
        albumMapper.updatePath(id, cover);
    }
}
