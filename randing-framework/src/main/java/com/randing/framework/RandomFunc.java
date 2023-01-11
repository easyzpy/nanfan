package com.randing.framework;

import cn.hutool.json.JSONObject;

import java.util.*;

/**
 * 代码生成器类
 *
 * @author flyxia
 * @version 1.0
 * @date 2020/3/23 15:53
 */
public class RandomFunc {


    public static void main(String[] args) {

        List<String> a = new ArrayList<String>();
//        for(int i=0;i<10000;i++)
//        {
//            Integer c = sample(new int[]{1,2});
//            a.add(c.toString());
            JSONObject[] ojb = new JSONObject[2];
            JSONObject json = new JSONObject();
            json.put("factor","体重");
            json.put("data","1,3");
            ojb[0]=json;
            JSONObject json2 = new JSONObject();
            json2.put("factor","身高");
            json2.put("data","1,2");
            ojb[1]=json2;
            int data[][]= dataFrame(100,ojb);

            int r[][] = grouping(1111,3,100,new int[]{1,2,1},2,data);



            for(int i=0; i<100; i++){
              for (int j=0; j<3; j++)
                System.out.printf("%5d",data[i][j]);
                //"%5d"表示按5位的固定位宽输出整型数值。如果不足五位，则在前面补空格；超过五位，则按实际位数输出k
                System.out.println();
            }
//        }
//        System.out.println(a);
//        System.out.println(frequencyOfListElements(a));

    }




    //受试者
    //timestamp 一个项目一个值，以便可追溯
    public static int[][] grouping(int timestamp ,int lamda,int n,int[] ratio,int factorCount, int[][] dataFrame)
    {
        //id,group,f1,f2,...fn
        int patient[][] =new int[n][2+factorCount];
        for(int i=0;i<n;i++)
        {
            patient[i][0]=dataFrame[i][0];
            patient[i][1]=1;
            for(int j=0;j<factorCount;j++)
            {
                patient[i][2+j]=dataFrame[i][j+1];
            }
        }

        int[] ratioCount= groupinfo(ratio.length,n,patient[0],patient);
        //id,runif,r1,r2.., (r1,r2 分到1，2组的概率)
        double runif[][]= new double[n][2+ratio.length];

        double b=0.0;
        for(int i=0;i<ratio.length;i++)
        {
            b+= ratio[i];
        }
        b= b*lamda;
        Random ra =new Random(timestamp); //模拟500次，要放到最外面
        for(int i=0;i<n;i++)
        {
            runif[i][0]=dataFrame[i][0];
            double a= ra.nextDouble();
            runif[i][1]=a;
            double m=0;
            for(int k=0;k<ratio.length;k++)
            {
               double numerator=  ratio[k]*lamda + ratio[k]*lamda*Math.ceil((i-1)/b)-ratioCount[k];
               double denominator = b+ b* Math.ceil((i-1)/b) -(i-1);
               m += numerator/denominator;
               runif[i][k+2] = m;
            }

            for(int k=0;k<ratio.length;k++)
            {
                if(a < runif[i][k+2])
                {
                    patient[i][1]=k;
                }
            }
        }
        // 分配结束  保存 patient，runif

        //

        //卡方校验 python实现 越大越好  >0.05
        //from  scipy.stats import chi2_contingency
        //import numpy as np
        //kf_data = np.array([[37,27], [39,21]])
        //kf = chi2_contingency(kf_datad)
        //print('chisq-statistic=%.4f, p-value=%.4f, df=%i expected_frep=%s'%kf)
        //# chisq-statistic=0.4054, p-value=0.5243, df=1 expected_frep=[[39.22580645 24.77419355][36.77419355 23.22580645]]
        //————————————————
        //版权声明：本文为CSDN博主「天马行空_ljt」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/qq_38214903/article/details/82967812


        //cg 猜对概率，越小越好
        return null;
    }


    //查找与当前人因素一致的分组情况
    public static int[] groupinfo(int ratioCount,int n,int[] cur,int pt[][])
    {
        int ratio[] = new int[ratioCount];

        for(int i=0;i<n;i++)
        {
            if(pt[i][1]==-1){
                continue;
            }
            int k=0;
            for(int j=0;j<cur.length-2;j++)
            {
                if(pt[i][j+2]==cur[j+2])
                    k++;
            }
            if(k==cur.length-2)
            {
                ratio[pt[i][1]-1]++;
            }
        }
        return ratio;
    }


    //生成受试者
    //ojb  [{"factor":"体重","data":"1,3"},{"factor":"身高","data":"1,2"}]
    public static int[][] dataFrame(int n, JSONObject[] ojb)
    {
        int len = ojb.length;
        int frm[][] = new int[n][len+1];
        for(int k=0;k<n;k++)
        {
            frm[k][0]=k;
            for(int i=0;i<len;i++)
            {
                JSONObject obj = ojb[i];
                String dd = obj.get("data").toString();
                String[] sz = dd.split(",");
                int a[] = new int[sz.length];
                for(int j=0;j<sz.length;j++)
                {
                    a[j]=Integer.parseInt(sz[j]);
                }
                frm[k][i+1]=sample(a);
            }
        }
        return frm;
    }


    /// data[] ={1,2}
    public static int sample(int data[])
    {
        int len = data.length;
        int dataSum =0;
        for(int i=0;i<len;i++)
        {
            dataSum+= data[i];
        }
        double prop[] =new double[len];
        prop[0]=(double) data[0]/dataSum;
        for(int i=1;i<len;i++)
        {
            int c = 0;
            for(int j=0;j<=i;j++)
            {
                c += data[j];
            }
            prop[i]=c/dataSum;
        }

        Random ra =new Random();
        double a= ra.nextDouble();
        //System.out.print(a+" ");
        int res =10;
        for(int i=0;i<len;i++)
        {
            if(i==0)
            {
                if(a>=0 && a<prop[i])
                {
                    res =1;
                    break;
                }
            }
            else
            {
                if(a>=prop[i-1] && a<prop[i])
                {
                    res = i+1;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * java统计List集合中每个元素出现的次数
     * 例如frequencyOfListElements(["111","111","222"])
     *  ->
     * 则返回Map {"111"=2,"222"=1}
     * @param items
     * @return  Map<String,Integer>
     * @author wuqx
     */
    public static Map<String,Integer> frequencyOfListElements(List<String> items ) {
        if (items == null || items.size() == 0) return null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String temp : items) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        return map;
    }
}