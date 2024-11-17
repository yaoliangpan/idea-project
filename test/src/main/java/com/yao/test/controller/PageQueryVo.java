package com.yao.test.controller;

import lombok.Data;

@Data
public class PageQueryVo {
    private Integer pageSize;//每页显示的条数
    private Integer page;//当前页数
    private String name;//模糊查询的名字
    private String userName;//模糊查询的年龄
}
