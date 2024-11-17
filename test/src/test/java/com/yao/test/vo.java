package com.yao.test;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class vo {
    private String userName;
    private String passWord;
    private Long age;
    // 0:离线 1:在线
    private int stutus;
    private LocalDateTime createDate;
}
