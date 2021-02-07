package com.zaw.superarch.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zhangaiwen
 */
@Api(tags = "文件服务")
@ApiSupport(author = "aiwen")
@RestController
@RequestMapping("file")
public class FileController {

    @Operation(summary = "文件上传")
    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) {
        file.getSize();
    }

    @Operation(summary = "文件素材上传接口")
    @PostMapping(value = "/uploadMaterial")
    public void uploadMaterial(@RequestParam(value = "file[]") MultipartFile[] files, @RequestParam(value = "title") String title, HttpServletRequest request) throws IOException {

    }

}
