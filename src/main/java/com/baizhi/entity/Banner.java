package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors
public class Banner {
    @Excel(name = "主键", needMerge = true)
    private String id;
    @Excel(name = "图片", type = 2, needMerge = true)
    private String img_path;
    @Excel(name = "标题", needMerge = true)
    private String title;
    @Excel(name = "状态", needMerge = true)
    private String status;
    @Excel(name = "描述信息", needMerge = true)
    private String decs;
    @Excel(name = "创建时间", format = "yyyy-MM-dd", width = 20, needMerge = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date create_date;
}
