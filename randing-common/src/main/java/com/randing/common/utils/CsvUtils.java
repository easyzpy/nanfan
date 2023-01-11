package com.randing.common.utils;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 处理并记录日志文件
 * 
 * @author randing
 */
public class CsvUtils
{
    public static File createCSVFile(List exportData, LinkedHashMap rowMapper, String outPutPath, String filename) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
             //创建文件
            csvFile = new File(outPutPath + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "GB2312"), 1024);
             // 写入文件头部
            for (Iterator propertyIterator = rowMapper.entrySet().iterator(); propertyIterator.hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                csvFileOutputStream.write("\""+ propertyEntry.getValue().toString() + "\"");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
                 LinkedHashMap row = (LinkedHashMap) iterator.next();
                System.out.println(row);
                for (Iterator propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext();) {
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                    csvFileOutputStream.write("\""+ propertyEntry.getValue().toString() + "\"");
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    public static File createCSVFile(String data, String path, String filename) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            //创建文件
            csvFile = new File(path + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "GB2312"), 1024);
            csvFileOutputStream.write(data);
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    public static File createCsvFile(String data, String path, String filename) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            //创建文件
            csvFile = new File(path + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "GB2312"), 1024);
            csvFileOutputStream.write(data);
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 创建文件
     * @param data  通过‘\n’换行，通过‘,’分隔列
     * @param basedir
     * @param name
     */
    public static void writeCsvFile(String data, String basedir, String name) {
        try {
            if(basedir==null || basedir=="")
            {
                basedir ="D:";
            }
            File file = new File(basedir + "/" + name);
            if (!file.exists()) {
                //创建文件
                file.createNewFile();
            }
            FileOutputStream  out = new FileOutputStream (file);
            byte[] bom ={(byte) 0xEF,(byte) 0xBB,(byte) 0xBF};
            out.write(bom);
            out.write((new String(data.getBytes(), "utf-8")).getBytes());
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

