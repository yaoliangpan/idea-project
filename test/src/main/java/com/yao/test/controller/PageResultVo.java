package com.yao.test.controller;

import lombok.Data;

@Data
public class PageResultVo {
    private Long total=4L;
    private vo[] records;
}
