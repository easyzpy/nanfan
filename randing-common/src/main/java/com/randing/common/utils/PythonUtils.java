package com.randing.common.utils;

import com.randing.common.core.text.StrFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Python工具类
 * 
 * @author flyxia
 */
public class PythonUtils
{
    public static void exePython(String cmd,String path){
        System.out.println("正在执行python程序");
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(cmd,null,new File(path));// 执行py文件
            Thread thread1 = new Thread(new StreamReaderThread(proc.getInputStream(),"d:\\normal.txt"));
            Thread thread2 = new Thread(new StreamReaderThread(proc.getErrorStream(),"d:\\error.txt"));
            thread2.start();
            thread1.start();//必须后执行，否则正确消息容易接收不到
            proc.waitFor();
            Thread.sleep(1000);//等待后台线程读写完毕
            System.out.println("python program done!!!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                proc.getErrorStream().close();
                proc.getInputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            proc.destroy();
        }
    }

    /**
     * 与当前受试者因素一致的各分组人数统计-PBD
     * @param pyFile  python文件路径  D:\demo2.py
     * @param parm  参数
     * @return
     * @author flyxia
     */
    public static String runPythonWithParam(String pyFile,String parm) {
        String res = "";
        try {
            String[] args = new String[]{"python", pyFile, String.valueOf(parm)};
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
                res +=line;
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }
}