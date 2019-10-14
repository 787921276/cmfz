package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @RequestMapping("/findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        Integer count = albumService.count();

        Integer total = count % rows == 0 ? count / rows : count / rows + 1;

        List<Album> byPage = albumService.findByPage(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", byPage);
        map.put("records", count);
        map.put("page", page);
        map.put("total", total);
        return map;

    }

    @RequestMapping("/edit")
    public String edit(Album album, String oper, String[] id) {
        if (oper.equals("add")) {
            String s = UUID.randomUUID().toString();
            album.setId(s);
            albumService.save(album);
            return s;
        } else if (oper.equals("del")) {
            albumService.delete(id);
        } else {
            albumService.updata(album);
        }
        return null;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile cover, String bannerId, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img");

        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = cover.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        try {
            cover.transferTo(new File(realPath, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        albumService.updatePath(bannerId, newFileName);
    }

}
