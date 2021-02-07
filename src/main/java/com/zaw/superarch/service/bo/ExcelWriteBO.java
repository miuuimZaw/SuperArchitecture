package com.zaw.superarch.service.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.zaw.superarch.converter.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Excel写对象
 *
 * @author zhangaiwen
 */
@AllArgsConstructor
@Data
public class ExcelWriteBO {

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("数值")
    private Double num;

    @ExcelProperty(value = "时间", converter = LocalDateTimeConverter.class)
    private LocalDateTime time;
}
