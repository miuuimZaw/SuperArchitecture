package com.zaw.superarch.controller;

import com.zaw.superarch.anno.LogOut;
import com.zaw.superarch.controller.resp.Result;
import com.zaw.superarch.service.IExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/**
 * @author zhangaiwen
 */
@Log4j2
@Api(tags = "Excel服务")
@RestController
@RequestMapping("excel")
public class ExcelController {

    @Resource
    private IExcelService excelService;

    @LogOut(description = "读Excel")
    @Operation(summary = "读Excel")
    @PostMapping("/read")
    public Result readExcel(@RequestPart("file") MultipartFile file) {
        StopWatch watch = new StopWatch();
        watch.start();
        excelService.readExcel(file);
        watch.stop();
        log.info("读Excel...耗时{}秒", watch.getTotalTimeSeconds());
        return Result.success();
    }

    @LogOut(description = "写Excel")
    @ApiOperation(value = "写Excel")
    @GetMapping(value = "/write", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> writeExcel() {
        StopWatch watch = new StopWatch();
        watch.start();
        // todo knife4j文件下载有点问题,一直下载的是knife.txt   knife4j还是有点问题
        // todo 文件上传用get请求，为什么文件特别大都不会超过get请求的限制
        ByteArrayOutputStream bos = excelService.writeExcel();
        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = new String(("aaa_" + LocalDate.now() + ".xlsx").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        watch.stop();
        log.info("写Excel...耗时{}秒", watch.getTotalTimeSeconds());
        return new ResponseEntity<>(bos.toByteArray(), httpHeaders, HttpStatus.CREATED);
    }
}
