package com.yao.test.controller;

import com.yao.test.common.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping("/download")
@RestController
@Slf4j
public class DownloadFile {


    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(String fileName) {
        log.info(BaseContext.getThreadLocal()+"的下载文件操作:{}", fileName);
            // 处理文件下载的功能
                try {
            // 文件路径
            Path filePath = Paths.get("D:\\服务器资源\\"+fileName);
            File file = filePath.toFile();// 文件对象

            // 创建 Resource 对象
            Resource resource = new FileSystemResource(file);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);

            // 返回 ResponseEntity
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
}
