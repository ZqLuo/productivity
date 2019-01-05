package com.productivity.util.easyexcel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * @Author luozeqiang
 * @Description 工具类
 */
public class ExcelUtil {

    /**
     * 读取 Excel(多个 sheet)
     * @param excel
     * @param clazz
     * @param headLineNum 行标题所在行，-1表示为空
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T extends BaseRowModel> List<T> readExcel(File excel, int headLineNum, Class<T> clazz) throws Exception {
        return readExcel(excel.getName(),new FileInputStream(excel), clazz,headLineNum);
    }

    /**
     * 读取excel文件
     * @param fileName
     * @param inputStream
     * @param tClass
     * @param headLineNum 行标题所在行，-1表示为空
     * @param <T>
     * @return
     */
    public static <T extends BaseRowModel> List<T> readExcel(String fileName, InputStream inputStream, Class<T> tClass, int headLineNum){
        ExcelListener<T> excelListener = new ExcelListener();
        ExcelReader reader = getReader(fileName,inputStream,excelListener);
        if (reader == null) {
            return null;
        }
        for (Sheet sheet : reader.getSheets()) {
            sheet.setClazz(tClass);
            sheet.setHeadLineMun(-1);
            reader.read(sheet);
        }
        return excelListener.getDatas();
    }

    /**
     * 生成excle文件
     * @param sheetName
     * @param list
     * @param clazz
     * @throws Exception
     */
    public static  void writeExcel(OutputStream out, String sheetName, List<? extends BaseRowModel> list, Class clazz) throws Exception {
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1, 0, clazz);
            sheet.setSheetName(sheetName);
            writer.write(list, sheet);
            writer.finish();
        } finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static ExcelReader getReader(String fileName, InputStream inputStream, ExcelListener excelListener){
        ExcelTypeEnum excelTypeEnum = ExcelTypeEnum.XLSX;
        if (fileName.toLowerCase().endsWith(".xls")) {
            excelTypeEnum = ExcelTypeEnum.XLS;
        }
        return new ExcelReader(inputStream, excelTypeEnum,null, excelListener);
    }

}
