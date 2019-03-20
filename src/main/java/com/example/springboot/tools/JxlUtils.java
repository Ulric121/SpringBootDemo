package com.example.springboot.tools;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.*;
import jxl.write.Number;
import jxl.write.*;
import jxl.write.WritableFont.FontName;
import jxl.write.biff.RowsExceededException;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: excel工具类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/1/14
 */
public class JxlUtils {

    private static String defaultEncoding = "UTF-8";

    /**
     * 创建工作簿，用于response的输出流返回
     *
     * @param response HttpServletResponse
     * @param fileName 文件名
     * @return
     */
    public static WritableWorkbook createWorkBook(HttpServletResponse response, String fileName) {

        OutputStream os = null;
        BufferedOutputStream bos = null;
        WritableWorkbook wwb = null;
        try {
            os = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName));

            bos = new BufferedOutputStream(os);
            wwb = createWorkBook(bos);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                    bos = null;
                }
                if (os != null) {
                    os.close();
                    os = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return wwb;
    }

    /**
     * 创建工作簿，用于导出文件到某个路径
     *
     * @param exportPath
     * @return
     */
    public static WritableWorkbook createWorkBook(String exportPath) {
        File file = new File(exportPath);
        if (!file.exists() || file.isDirectory()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        WritableWorkbook wwb = createWorkBook(file);
        return wwb;
    }

    /**
     * 创建WritableWorkbook
     *
     * @param obj
     * @return
     */
    private static WritableWorkbook createWorkBook(Object obj) {
        if (obj == null) {
            return null;
        }

        System.out.println("创建工作簿WritableWorkbook开始...");

        WorkbookSettings setting = new WorkbookSettings();
        setting.setEncoding(defaultEncoding);

        WritableWorkbook wwb = null;
        try {

            if (obj instanceof File) {
                File file = (File) obj;
                wwb = Workbook.createWorkbook(file, setting);
            } else if (obj instanceof BufferedOutputStream) {
                BufferedOutputStream bos = (BufferedOutputStream) obj;
                wwb = Workbook.createWorkbook(bos, setting);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("创建工作簿WritableWorkbook结束...");

        return wwb;
    }

    /**
     * 根据SheetNames数量创建对应数量的sheet
     *
     * @param sheetNames
     * @param wwb
     * @return
     */
    public static List<WritableSheet> createSheet(String[] sheetNames, WritableWorkbook wwb) {
        if (sheetNames == null || sheetNames.length == 0) {
            return null;
        }
        if (wwb == null) {
            return null;
        }
        int sheetNum = sheetNames.length;
        System.out.println("Excel创建sheet数量：" + sheetNum);

        List<WritableSheet> list = new ArrayList<WritableSheet>(sheetNum);

        for (int i = 0; i < sheetNum; i++) {
            WritableSheet ws = wwb.createSheet(sheetNames[i], i);
            ws.getSettings().setDefaultColumnWidth(25);
            list.add(ws);
        }

        return list;

    }

    /**
     * 创建title格式
     *
     * @return
     */
    public static WritableCellFormat getTitleFormat() {

        System.out.println("创建title格式...");

        FontName fontName = WritableFont.createFont("宋体");
        int fontSize = 15;
        // 是否斜体
        boolean isItalic = false;

        // 参数依次是：字体设置/字体大小/字体粗细/是否是斜体/下划线类型/颜色
        WritableFont titleFont = new WritableFont(fontName, fontSize, WritableFont.BOLD, isItalic,
                UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat titleFormat = new WritableCellFormat(titleFont);

        return titleFormat;
    }

    /**
     * 获取内容格式
     *
     * @return
     */
    public static WritableCellFormat getContentFormat() {

        System.out.println("创建内容格式...");

        WritableCellFormat contentFormat = new WritableCellFormat();
        try {
            // 是否换行
            contentFormat.setWrap(true);
            // 全框/细线/黑色
            contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            // 水平居左
            contentFormat.setAlignment(Alignment.LEFT);
        } catch (WriteException e) {
            e.printStackTrace();
        }

        return contentFormat;
    }

    /**
     * 单元格插入内容
     *
     * @param sheet       表单对象
     * @param columnIndex 列
     * @param rowIndex    行
     * @param content     内容
     * @param format      格式
     */
    public static void addCell(WritableSheet sheet, int columnIndex, int rowIndex, Object content,
                               WritableCellFormat format) {

        WritableCell cell = null;

        if (content instanceof Double) {
            if (format != null) {
                cell = new Number(columnIndex, rowIndex, (Double) content, format);
            } else {
                cell = new Number(columnIndex, rowIndex, (Double) content);
            }

        } else if (content instanceof Date) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(content);

            if (format != null) {
                cell = new Label(columnIndex, rowIndex, dateString, format);
            } else {
                cell = new Label(columnIndex, rowIndex, dateString);
            }

        } else if (content instanceof Integer) {
            if (format != null) {
                cell = new Number(columnIndex, rowIndex, (Integer) content, format);
            } else {
                cell = new Number(columnIndex, rowIndex, (Integer) content);
            }

        } else {
            if (content == null) {
                content = "";
            }
            if (format != null) {
                cell = new Label(columnIndex, rowIndex, (String) content, format);
            } else {
                cell = new Label(columnIndex, rowIndex, (String) content);
            }
        }

        try {
            sheet.addCell(cell);
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析一个list对象插入到sheet
     *
     * @param sheet
     * @param list
     * @param titles      列名 空列填""
     * @param clazzFields class类的属性名："getXXX"格式 空列填""
     */
    public static void parseClass(WritableSheet sheet, List list, String[] titles, String[] clazzFields) {
        if (sheet == null || list == null || titles == null || clazzFields == null) {
            return;
        }

        WritableCellFormat format = getContentFormat();
        // 默认第一行还有个大标题~，如果不需要那个大标题，此处改成0
        int rowIndex = 0;
        // 先将列名插入sheet
        for (int columnIndex = 0; columnIndex < titles.length; columnIndex++) {
            addCell(sheet, columnIndex, rowIndex, titles[columnIndex], format);
        }

        rowIndex++;

        System.out.println("遍历传入的list对象，并写入sheet表单开始...");
        for (int i = 0; i < list.size(); i++) {

            int startColumn = 0;
            if ("序号".equals(titles[0])) {
                addCell(sheet, startColumn, rowIndex, (i + 1) + "", format);
                startColumn++;
            }
            Object obj = list.get(i);
            // 将传入的clazzFields从clazz取出来
            for (int columnIndex = startColumn; columnIndex < clazzFields.length; columnIndex++) {

                if ("".equals(clazzFields[columnIndex])) {// 如果属性没写，默认填一空行
                    addCell(sheet, columnIndex, rowIndex, "", format);
                    continue;
                }

                // 属性不为空，反射出类的属性，取值
                try {
                    Method method = obj.getClass().getDeclaredMethod(clazzFields[columnIndex], null);
                    Object field = method.invoke(obj, null);
                    addCell(sheet, columnIndex, rowIndex, field, format);

                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            rowIndex++;
        }
        System.out.println("遍历传入的list对象，并写入sheet表单结束...");
    }
}
