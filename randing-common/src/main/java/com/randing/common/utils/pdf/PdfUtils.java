package com.randing.common.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.randing.common.annotation.Excel;
import com.randing.common.annotation.Pdf;
import com.randing.common.config.RandingConfig;
import com.randing.common.constant.Constants;
import com.randing.common.constant.PdfConstants;
import com.randing.common.core.domain.AjaxResult;
import com.randing.common.exception.BaseException;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @Author patrick
 * @Description pdf导出工具类
 * @Date 2021/6/15
 * @Version 1.0
 */
public class PdfUtils {


    public static void setTitle(String title, Document document) {
        try {
            BaseFont bfChinese = BaseFont.createFont(PdfConstants.BASE_FONT_NAME, PdfConstants.BASE_FONT_ENCODING, BaseFont.NOT_EMBEDDED);
            document.add(new Chunk("\r"));
            document.add(new Paragraph(title, new Font(bfChinese)));
            document.add(new Chunk("\r"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBlank(String [] title, Document document) {
        try {
            BaseFont bfChinese = BaseFont.createFont(PdfConstants.BASE_FONT_NAME, PdfConstants.BASE_FONT_ENCODING, BaseFont.NOT_EMBEDDED);
            document.add(new Chunk("\r"));
            document.add(new Paragraph(title[Constants.ZERO], new Font(bfChinese)));
            document.add(new Chunk("\t"));
            document.add(new Paragraph(title[Constants.ONE], new Font(bfChinese)));
            document.add(new Chunk("\r"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Document getDocumentExport(String fileName) {
        OutputStream out = null;
        Document document = new Document();
        try {
            fileName =UUID.randomUUID().toString() + "_" + fileName + ".pdf";
            System.out.println(fileName);
            System.out.println("--------------------------");
            out = new FileOutputStream(getAbsoluteFile(fileName));
            //构建一个PDF文档输出流程
            PdfWriter.getInstance(document, out);
            //打开PDF文件流
            document.open();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public static String getAbsoluteFile(String filename) {
        String downloadPath = RandingConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    public static Document getDocument() {
        Document document = new Document();
        try {
            //构建一个PDF文档输出流程
            OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\patrickzh\\Desktop\\test02.pdf"));
            PdfWriter.getInstance(document, outputStream);
            //打开PDF文件流
            document.open();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Document getDocumentDownload(ServletOutputStream outputStream) {
        Document document = new Document();
        try {

            //构建一个PDF文档输出流程
            PdfWriter.getInstance(document, outputStream);
            //打开PDF文件流
            document.open();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Document getDocumentDownload(String pdfFile) {
        Document document = new Document();
        try {

            //构建一个PDF文档输出流程
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(pdfFile));
            //打开PDF文件流
            document.open();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ServletOutputStream getOutputStream(HttpServletResponse response, String fileName) {
        try {
            fileName = URLEncoder.encode(UUID.randomUUID().toString() + "_" + fileName + ".pdf", "UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/pdf;charset=utf-8");
            return outputStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static PdfPTable getTable(List<? extends Object> list) {
        try {
            HashMap<String, List> stringObjectHashMap = new HashMap<>(list.size() * 2);
            ArrayList<HashMap> fieldValue = new ArrayList<>();
            list.forEach(o -> {
                Class<?> aClass = o.getClass();
                Field[] declaredFields = aClass.getDeclaredFields();
                ArrayList<String> name = new ArrayList<>();
                HashMap<String, Object> stringObjectHashMap1 = new HashMap<>(list.size() * 2);
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(Pdf.class)) {
                        Pdf annotation = field.getAnnotation(Pdf.class);
                        name.add(annotation.name());
                        field.setAccessible(true);
                        try {
                            if (!"".equals(annotation.readConverterExp())) {
                                String s = String.valueOf(field.get(o));
                                String[] split = annotation.readConverterExp().split(",");
                                for (String s1 : split) {
                                    String[] split1 = s1.split("=");
                                    for (int i = 0; i < split1.length; i++) {
                                        if (i % 2 == 0) {
                                            if (s.equals(split1[i])) {
                                                stringObjectHashMap1.put(annotation.name(), split1[i + 1]);
                                            } else if (split[i] == null) {
                                                stringObjectHashMap1.put(annotation.name(), "N/A");
                                            }
                                        }
                                    }
                                }
                            } else if (field.get(o) == null) {
                                stringObjectHashMap1.put(annotation.name(), "N/A");
                            } else {
                                stringObjectHashMap1.put(annotation.name(), field.get(o));
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                fieldValue.add(stringObjectHashMap1);
                stringObjectHashMap.put("fieldName", name);

            });
            //设置字体样式
            BaseFont bfChinese = BaseFont.createFont(PdfConstants.BASE_FONT_NAME, PdfConstants.BASE_FONT_ENCODING, BaseFont.NOT_EMBEDDED);
            Font f8 = new Font(bfChinese, 8, Font.NORMAL);
            List<String> fieldName = stringObjectHashMap.get(PdfConstants.FIELD_NAME);
            //创建一个N列的表格控件
            PdfPTable table = new PdfPTable(fieldName.size());
            //设置表格占PDF文档100%宽度
            table.setWidthPercentage(100);
            ArrayList<PdfPRow> rows = table.getRows();
            //设置表格控件水平方向左对齐
            table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
            //创建一个表格的表头单元格
            PdfPCell pdfTableHeaderCell = new PdfPCell();
            //设置表格的表头单元格颜色
//            pdfTableHeaderCell.setBackgroundColor(new BaseColor(213, 141, 69));
            pdfTableHeaderCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

            fieldName.forEach(s -> {
                pdfTableHeaderCell.setPhrase(new Paragraph(s, f8));
                table.addCell(pdfTableHeaderCell);
            });
            fieldValue.forEach(hashMap -> {
                //创建一个表格的正文内容单元格
                PdfPCell pdfTableContentCell = new PdfPCell();
                pdfTableContentCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                pdfTableContentCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                fieldName.forEach(s -> {
                    pdfTableContentCell.setPhrase(new Paragraph(String.valueOf(hashMap.get(s)), f8));
                    table.addCell(pdfTableContentCell);
                });

            });
            return table;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 创建单元格，添加到表格,默认字体居中,背景色白色
     *
     */
    public static PdfPCell getCell(String content) {
        PdfPCell cell;
        cell = new PdfPCell(new Paragraph(content,new Font()));
        cell.setFixedHeight(28);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPaddingLeft(0);
        return cell;
    }


}
