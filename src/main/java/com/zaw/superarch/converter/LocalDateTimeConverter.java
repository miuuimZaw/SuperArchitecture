package com.zaw.superarch.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.zaw.superarch.anno.LocalDateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * EasyExcel自定义LocalDateTime转换器
 *
 * @author zhangaiwen
 */
public class LocalDateTimeConverter implements Converter {

    /**
     * 不使用{@code @LocalDateTimeFormat}注解指定日期格式时,默认使用该格式.
     */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 读调用
     *
     * @param cellData            excel数据 (NotNull)
     * @param contentProperty     excel属性 (Nullable)
     * @param globalConfiguration 全局配置 (NotNull)
     * @return 读取到内存中的数据
     */
    @Override
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        LocalDateTimeFormat annotation = contentProperty.getField().getAnnotation(LocalDateTimeFormat.class);
        return LocalDateTime.parse(cellData.getStringValue(),
                DateTimeFormatter.ofPattern(Objects.nonNull(annotation) ? annotation.value() : DEFAULT_PATTERN));
    }

    /**
     * 写调用
     *
     * @param o                    java value (NotNull)
     * @param excelContentProperty excel属性 (Nullable)
     * @param globalConfiguration  全局配置 (NotNull)
     * @return 写出到excel文件的数据
     */
    @Override
    public CellData convertToExcelData(Object o, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        LocalDateTimeFormat annotation = excelContentProperty.getField().getAnnotation(LocalDateTimeFormat.class);
        LocalDateTime time = (LocalDateTime) o;
        return new CellData(time.format(DateTimeFormatter.ofPattern(Objects.nonNull(annotation) ? annotation.value() : DEFAULT_PATTERN)));
    }
}
