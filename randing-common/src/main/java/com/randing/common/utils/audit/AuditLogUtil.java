package com.randing.common.utils.audit;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.randing.common.annotation.FieldData;
import com.randing.common.utils.DateUtil;
import com.randing.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

public class AuditLogUtil {

    /**
     *  新增删除 获取对象信息
     * @param object 对象数据
     * @return
     * @throws Exception
     */
    public static String objectToString(Object object) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Class<?> objClass = object.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields){
            if (field.isAnnotationPresent(FieldData.class)) {
                FieldData annotation = field.getAnnotation(FieldData.class);
                field.setAccessible(true);
                Map<String, Object> map = new HashMap<String, Object>();

                if (StringUtils.isNotEmpty(annotation.description())){

                    if ("date".equals(annotation.description())){
                        Type genericType = field.getGenericType();

                        if ("class java.util.Date".equals(genericType.toString())){
                            Object value =  field.get(object);
                            if (ObjectUtils.isNotEmpty(value)){
                                map.put("name", annotation.name());
                                map.put("value", value == null ? "" : DateUtil.dateTimeFormat((Date)value, "yyyy-MM-dd"));
                                list.add(map);
                            }
                            continue;
                        }

                        if ("class java.time.LocalDate".equals(genericType.toString())){
                            Object value =  field.get(object);
                            if (ObjectUtils.isNotEmpty(value)){
                                map.put("name", annotation.name());
                                map.put("value", value == null ? "" : value.toString());
                                list.add(map);
                            }
                            continue;
                        }

                        if ("class java.time.LocalDateTime".equals(genericType.toString())){
                            LocalDateTime value = (LocalDateTime) field.get(object);
                            if (ObjectUtils.isNotEmpty(value)){
                                map.put("name", annotation.name());
                                map.put("value", value == null ? "" : DateUtil.localDateTimeFormatyMdHms(value));
                                list.add(map);
                            }
                            continue;
                        }

                    }

                    Map<String, String> converter = new HashMap<String, String>();

                    String[] strings = annotation.description().split(",");
                    for (String string : strings){
                        String[] split = string.split("=");
                        converter.put(split[0], split[1]);
                    }



                    String value = converter.get(field.get(object));

                    if (ObjectUtils.isNotEmpty(value)){
                        map.put("name", annotation.name());
                        map.put("value", value == null ? "" : value);
                        list.add(map);
                    }
                    continue;
                }

                if (ObjectUtils.isNotEmpty(field.get(object))){
                    map.put("name", annotation.name());
                    map.put("value", field.get(object));
                }

                if (ObjectUtils.isNotEmpty(map))
                    list.add(map);
            }
        }

        StringBuilder contentObject = new StringBuilder();


        if (CollectionUtils.isNotEmpty(list) ){
            for(Map<String, Object> map : list){
                contentObject.append(map.get("name")+"---" + map.get("value")+"###");
            }
        }

        String content = new String();

        if (ObjectUtils.isNotEmpty(contentObject)){
            content = contentObject.substring(0,contentObject.length()-3).toString();
        }

        return content;

    }







    /**
     *  修改 新旧数据对比
     * @param oldObject 老数据
     * @param newObject 新数据
     * @return
     * @throws Exception
     */
    public static Map<String,String> compareTwoClass(Object oldObject, Object newObject) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Class<?> oldClass = oldObject.getClass();
        Class<?> newClass = newObject.getClass();

        Field[] oldFields = oldClass.getDeclaredFields();
        Field[] newFields = newClass.getDeclaredFields();

        for (int i = 0; i < oldFields.length; i++) {
            if (oldFields[i].isAnnotationPresent(FieldData.class)) {
                for (int j = 0; j < newFields.length; j++) {
                    if (oldFields[i].getName().equals(newFields[j].getName())) {
                        oldFields[i].setAccessible(true);
                        newFields[j].setAccessible(true);
                        if (!compareTwoParam(oldFields[i].get(oldObject), newFields[j].get(newObject))) {
                            FieldData annotation = oldFields[i].getAnnotation(FieldData.class);
                            Map<String, Object> map = new HashMap<String, Object>();

                            if (StringUtils.isNotEmpty(annotation.description())){

                                if ("date".equals(annotation.description())){
                                    Type genericType = oldFields[i].getGenericType();

                                    if ("class java.util.Date".equals(genericType.toString())){
                                        Object dateOld =  oldFields[i].get(oldObject);
                                        Object dateNew =  newFields[i].get(newObject);
                                        map.put("name", annotation.name());
                                        map.put("old", oldFields[i].get(oldObject) == null ? "" : DateUtil.dateTimeFormat((Date)dateOld, "yyyy-MM-dd"));
                                        map.put("new", newFields[i].get(newObject) == null ? "" : DateUtil.dateTimeFormat((Date)dateNew, "yyyy-MM-dd"));
                                        list.add(map);
                                        continue;
                                    }

                                    if ("class java.time.LocalDate".equals(genericType.toString())){
                                        Object dateOld =  oldFields[i].get(oldObject);
                                        Object dateNew =  newFields[i].get(newObject);
                                        map.put("name", annotation.name());
                                        map.put("old", oldFields[i].get(oldObject) == null ? "" : dateOld.toString());
                                        map.put("new", newFields[i].get(newObject) == null ? "" : dateNew.toString());
                                        list.add(map);
                                        continue;
                                    }
                                    if ("class java.time.LocalDateTime".equals(genericType.toString())){
                                        LocalDateTime dateOld = (LocalDateTime) oldFields[i].get(oldObject);
                                        LocalDateTime dateNew = (LocalDateTime) newFields[i].get(newObject);
                                        map.put("name", annotation.name());
                                        map.put("old", oldFields[i].get(oldObject) == null ? "" : DateUtil.localDateTimeFormatyMdHms(dateOld));
                                        map.put("new", newFields[i].get(newObject) == null ? "" : DateUtil.localDateTimeFormatyMdHms(dateNew));
                                        list.add(map);
                                        continue;
                                    }

                                }

                                Map<String, String> converter = new HashMap<String, String>();

                                String[] strings = annotation.description().split(",");
                                for (String string : strings){
                                    String[] split = string.split("=");
                                    converter.put(split[0], split[1]);
                                }

                                map.put("name", annotation.name());
                                map.put("old", oldFields[i].get(oldObject) == null ? "" : converter.get(oldFields[i].get(oldObject).toString()));
                                map.put("new", newFields[i].get(newObject) == null ? "" : converter.get(newFields[i].get(newObject).toString()));
                                list.add(map);
                                continue;
                            }

                            map.put("name", annotation.name());
                            map.put("old", oldFields[i].get(oldObject) == null ? "" : oldFields[i].get(oldObject));
                            map.put("new", newFields[j].get(newObject) == null ? "" : newFields[j].get(newObject));
                            if (ObjectUtils.isNotEmpty(map))
                                list.add(map);
                        }
                    }
                }
            }
        }


        StringBuilder beforeContent = new StringBuilder();
        StringBuilder afterContent = new StringBuilder();


        if (CollectionUtils.isNotEmpty(list) ){
            for(Map<String, Object> map : list){
                if (ObjectUtils.isEmpty(map.get("old")) && ObjectUtils.isEmpty(map.get("new"))){
                    continue;
                }
                beforeContent.append(map.get("name")+"---" + map.get("old")+"###");
                afterContent.append(map.get("name")+"---" + map.get("new")+"###");
            }
        }

        String before = new String();
        String after = new String();

        if (ObjectUtils.isNotEmpty(beforeContent)){
            before = beforeContent.substring(0,beforeContent.length()-3).toString();
        }

        if (ObjectUtils.isNotEmpty(afterContent)){
            after = afterContent.substring(0,afterContent.length()-3).toString();
        }

        Map<String,String> map = new HashMap<>();
        map.put("before",before);
        map.put("after",after);


        return map;
    }


    /**
     *  数据比较
     * @param obj1  旧数据
     * @param obj2  新数据
     * @return
     */
    public static boolean compareTwoParam( Object obj1 , Object obj2){
        if (obj1 == null && obj2 == null){
            return true;
        }
        if (obj1 == null && obj2 != null) {
            return false;
        }
        if (obj1.equals(obj2)) {
            return true;
        }
        return false;
    }


}
