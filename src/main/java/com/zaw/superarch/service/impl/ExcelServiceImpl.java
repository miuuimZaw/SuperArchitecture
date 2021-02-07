package com.zaw.superarch.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.zaw.superarch.service.IExcelService;
import com.zaw.superarch.service.bo.ExcelReadBO;
import com.zaw.superarch.service.bo.ExcelWriteBO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel文件处理实现类
 *
 * @author zhangaiwen
 */
@Log4j2
@Service
public class ExcelServiceImpl implements IExcelService {

    /**
     * 读Excel
     *
     * @param file 文件
     */
    @Override
    public void readExcel(MultipartFile file) {
        List<ExcelReadBO> boList = new ArrayList<>();
        try {
            InputStream in = file.getInputStream();
            boList = EasyExcel.read(in).head(ExcelReadBO.class).doReadAllSync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boList.forEach(bo -> log.info("\n 读取文件...bo:{}", JSONObject.toJSONString(bo)));
    }

    /**
     * 写Excel
     *
     * @return 文件流
     */
    @Override
    public ByteArrayOutputStream writeExcel() {
        List<ExcelWriteBO> writeList = new ArrayList<>();
        writeList.add(new ExcelWriteBO("名称1", 12.45D, LocalDateTime.now()));
        writeList.add(new ExcelWriteBO("名称2", 12.00D, LocalDateTime.now()));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        EasyExcel.write(bos).head(ExcelWriteBO.class).excelType(ExcelTypeEnum.XLSX).sheet("写Excel").doWrite(writeList);
        return bos;
    }

}
