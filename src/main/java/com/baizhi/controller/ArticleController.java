package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("getAll")
    public Map<String, Object> getAll(Integer page, Integer rows) {
        Map<String, Object> map = articleService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("add")
    public void getAll(Article article) {
        String s = UUID.randomUUID().toString();
        article.setId(s);
        article.setCreateDate(new Date());
        articleService.add(article);
    }

    @RequestMapping("update")
    public void update(Article article) {
        articleService.update(article);
    }
}
