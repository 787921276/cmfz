package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Banner;
import com.baizhi.service.AdminService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    AdminService adminService;

    @RequestMapping("edit")
    public String edit(Banner banner, String imgPath, String oper) {
        if (oper.equals("add")) {
            String s = UUID.randomUUID().toString();
            adminService.save(banner);
            return s;
        }
        return null;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile img_path, String bannerId, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img");

        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = img_path.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        try {
            img_path.transferTo(new File(realPath, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        adminService.updatePath(bannerId, newFileName);
    }

    @ResponseBody
    @RequestMapping("/querall")
    public List findAll(HttpServletResponse response) throws IOException {
        List<Banner> querall = adminService.findAll(response);
        for (Banner banner : querall) {
            banner.setImg_path("D:\\学习\\三阶段\\wenjian\\cmfz\\cmfz\\src\\main\\webapp\\img\\" + banner.getImg_path());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图", "轮播图列表"),
                Banner.class, querall);
        String encode = URLEncoder.encode("轮播图信息.xls", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + encode);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        return null;

    }
}
