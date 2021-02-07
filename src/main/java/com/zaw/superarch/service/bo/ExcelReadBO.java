package com.zaw.superarch.service.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.zaw.superarch.converter.LocalDateTimeConverter;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Excel读对象
 *
 * @author zhangaiwen
 */
@Data
public class ExcelReadBO {

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty(value = "时间", converter = LocalDateTimeConverter.class)
    private LocalDateTime time;

    @ExcelProperty("数字")
    private Double num;

}
