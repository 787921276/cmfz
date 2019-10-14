package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors
public class user {
    private String id;
    private String dharname;
    private String name;
    private String sex;
    private String province;
    private String city;
    private String sign;
    private String status;
    private String creatDate;
    private String head_img;
    private String username;
    private String passwoed;
    private String guru_id;
}
