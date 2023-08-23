package com.randing.generator;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.mysql.cj.xdevapi.JsonArray;
import com.randing.common.exception.BaseException;

import java.util.*;

/**
 * 代码生成器类
 *
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/23 15:53
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public Random ra ;

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("keyin" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("please right:" + tip + "！");
    }


     public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
         GlobalConfig gc = new GlobalConfig();
        // 全局配置
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOutputDir("C:/src/main/java");
        gc.setAuthor("Leen");
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 是否打开输出目录 默认为true
        gc.setOpen(false);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://10.130.34.252:3306/nfland?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("nfland");
        dsc.setPassword("nfland@#123");
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        // pc.setModuleName(scanner("模块名"));
        pc.setParent("com.randing.system");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("domain.po");
        mpg.setPackageInfo(pc);

        // 配置模板
        //  TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        //   templateConfig.setXml(null);
        //   mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("com.fame.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // strategy.setSuperControllerClass("com.fame.common.BaseController");
        strategy.setInclude(scanner("tablename，more").split(","));
        strategy.setSuperEntityColumns("id");
        // strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("sys");
        mpg.setStrategy(strategy);
        //   mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

//    public static void main(String[] args) {
//
//
//        List<String> a = new ArrayList<String>();
////        for(int i=0;i<10000;i++)
////        {
////            Integer c = sample(new int[]{1,2});
////            a.add(c.toString());
//            JSONObject[] ojb = new JSONObject[2];
//            JSONObject json = new JSONObject();
//            json.put("factor","体重");
//            json.put("data","1,3");
//            ojb[0]=json;
//            JSONObject json2 = new JSONObject();
//            json2.put("factor","身高");
//            json2.put("data","1,2");
//            ojb[1]=json2;
//            int data[][]= dataFrame(100,ojb);
//
//            int r[][] = grouping(1111,3,100,new int[]{1,2,1},2,data);
//
//
//
//            for(int i=0; i<100; i++){
//              for (int j=0; j<3; j++)
//                System.out.printf("%5d",data[i][j]);
//                //"%5d"表示按5位的固定位宽输出整型数值。如果不足五位，则在前面补空格；超过五位，则按实际位数输出k
//                System.out.println();
//            }
////        }
////        System.out.println(a);
////        System.out.println(frequencyOfListElements(a));
//
//    }

}