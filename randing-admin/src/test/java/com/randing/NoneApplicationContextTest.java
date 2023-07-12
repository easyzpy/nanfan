package com.randing;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

public class NoneApplicationContextTest {
    @Test
    public void test25() {

        Configuration  configuration = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("UTF-8");
        // 模板文件所在路径
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        Template t = null;
        try {
            // 获取模板文件
            t = configuration.getTemplate("templates/123.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("text1", "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        // 导出文件
        File outFile = new File("C:\\Users\\28544\\Desktop\\"+UUID.randomUUID().toString()+".doc");
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8))) {
            if (t != null) {
                // 将填充数据填入模板文件并输出到目标文件
                t.process(dataMap, out);
            }
        } catch (IOException | TemplateException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void test23() {
//        MacSigner asdfasfasdfasdfsadf = new MacSigner("asdfasfasdfasdfsadf");
        String upperCase = UUID.randomUUID().toString().toUpperCase();
        System.out.println(upperCase);

    }


//    @Test
//    public void test24() {
//        Document doc = new Document();
//        doc.loadFromFile("F:\\yourlocation\\template.docx", FileFormat.Docx);
//
//        Map<String, String> map = new HashMap<>();
//        map.put("${name}", "张山");
//        map.put("${birthday}", "2021-10-18");
//        map.put("${result}", "成功");
//        map.put("${col}", "第一列");
//        map.put("${col1}", "第二列");
//        map.put("${col2}", "第三列");
//        replaceSpecialWord(doc, map);
//        // 保存为文件
//        doc.saveToFile("F:\\yourlocation\\result.docx",FileFormat.Docx);
//————————————————
//        版权声明：本文为CSDN博主「Jiutwo」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/qq_38431321/article/details/120894068
//    }
//    public void replaceSpecialWord(Document doc, Map<String, String> map) {
//        // 正则表达式，匹配所有的占位符 ${}
//        Pattern pattern = Pattern.compile("\\$\\{.*?}");
//        // 根据正则表达式获取所有文本
//        TextSelection[] allPattern = doc.findAllPattern(pattern);
//        // 逐个替换占位符
//        for (TextSelection textSelection : allPattern) {
//            String tmp = map.get(textSelection.getSelectedText());
//            System.out.print(textSelection.getSelectedText());
//            int res = doc.replace(textSelection.getSelectedText(), tmp, true, true);
//            System.out.println(": " + res);
//        }
//    }

}
