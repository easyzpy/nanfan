package com.randing.common.utils;

import java.io.*;

public class StreamReaderThread implements Runnable {
    /*
     * python的输出流
     */
    private InputStream inputStream;
    /*
     * 输出信息保存的文件名称
     */
    private String logName;

    public StreamReaderThread(InputStream inputStream,String logName){
        this.inputStream = inputStream;
        this.logName = logName;
    }
    public void run()
    {
        BufferedReader in = null;
        FileWriter fwriter = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(this.inputStream));
            fwriter = new FileWriter(logName, true);
            String line = null;
            while ( (line = in.readLine()) != null)
            {
                fwriter.write(line);
                System.out.println(line);
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                fwriter.flush();
                fwriter.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
