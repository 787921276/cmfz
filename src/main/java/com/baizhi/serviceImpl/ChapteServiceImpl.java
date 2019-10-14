package com.baizhi.serviceImpl;


import com.baizhi.entity.Album;
import com.baizhi.entity.chapter;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.ChapteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChapteServiceImpl implements ChapteService {
    @Autowired
    ChapterMapper chapterMapper;
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<chapter> findByPage(Integer page, Integer rows, String albumid) {
        Integer start = (page - 1) * rows;
        List<chapter> byPage = chapterMapper.findByPage(start, rows, albumid);
        return byPage;
    }

    @Override
    public Integer count(String albumid) {
        Integer count = chapterMapper.count(albumid);
        return count;
    }

    @Override
    public String save(chapter chapter) {
        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        chapter.setCreateDate(new Date());
        chapter.setLongTime("sdf");
        chapter.setSize("sss");
        chapterMapper.save(chapter);

        return s;
    }

    @Override
    public void updata(chapter chapter) {
        chapterMapper.updata(chapter);
        Integer count = chapterMapper.count(chapter.getAlbumid());
        chapter chapter1 = new chapter();
        chapter1.getAlbumid();
        Album album = new Album();
        album.setCount(count);
        album.setId(chapter.getAlbumid());
        albumMapper.updateCount(album);
    }

    @Override
    public void delete(String[] id) {
        chapterMapper.delete(id);
    }

}
