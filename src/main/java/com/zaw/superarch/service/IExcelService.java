package com.zaw.superarch.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

/**
 * Excel文件处理接口
 *
 * @author zhangaiwen
 */
public interface IExcelService {

    /**
     * 读Excel
     *
     * @param file 文件
     */
    void readExcel(MultipartFile file);

    /**
     * 写Excel
     *
     * @return 文件流
     */
    ByteArrayOutputStream writeExcel();
}
