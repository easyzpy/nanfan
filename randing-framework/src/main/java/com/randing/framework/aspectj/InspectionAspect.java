//package com.randing.framework.aspectj;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
//import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
//import com.randing.common.annotation.FieldData;
//import com.randing.common.annotation.InspectionLogger;
//import com.randing.common.core.domain.model.LoginUser;
//import com.randing.common.exception.BaseException;
//import com.randing.common.utils.DateUtil;
//import com.randing.common.utils.SecurityUtils;
//import com.randing.common.utils.ServletUtils;
//import com.randing.common.utils.StringUtils;
//import com.randing.common.utils.spring.SpringUtils;
//import com.randing.system.domain.po.SysAuditLog;
//import com.randing.system.service.common.TokenService;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ReflectionUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Aspect
//@Component
//@Order(2)
//public class InspectionAspect {
//
//    @Autowired
//    private SysAuditLogService sysAuditLogService;
//
//    @Pointcut("@annotation(com.randing.common.annotation.InspectionLogger)")
//    public void openLogger() {
//    }
//
//
//    @Around("openLogger()")
//    public Object addInspection(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Object proceed = null;
//        //获取登录用户
//        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
//
//        if (ObjectUtils.isNull(loginUser)) {
//            throw new BaseException("当前用户信息不能为空");
//        }
//
//        SysAuditLog sysAuditLog = new SysAuditLog();
//        List<Object> beforeObjects = new ArrayList<>();
//        List<List<Map<String, Object>>> lists = new ArrayList<>();
//
//        try {
//            //获取注解
//            InspectionLogger annotation = getAnnotation(joinPoint);
//
//            if (annotation == null) {
//                return proceed;
//            }
//
//            //获取请求参数
//            Object[] args = joinPoint.getArgs();
//
//            //如果key不为空，则是删除或者修改
//            if (StringUtils.isNotEmpty(annotation.parameterKey())) {
//
//                String key = annotation.parameterKey();
//                String queryMethod = annotation.queryMethod();
//                String serviceClass = annotation.serviceClass();
//                String requestMethod = annotation.type();
//
//                //删除
//                if ("Delete".equals(requestMethod)) {
//                    String value = "";
//                    value = JSONObject.toJSONString(args[0]);
//                    // sysAuditLog.setModelId(value);
//                    //批量删除
//                    if (value.contains(",")) {
//                        value = value.substring(1, value.length() - 1);
//                        String[] str = value.split(",");
//                        for (String string : str) {
//                            // 获取修改前的数据放入 beforeObjects
//                            beforeObjects.add(getBeforeData(serviceClass, queryMethod, string));
//                        }
//                    } else {
//                        // 获取修改前的数据放入 beforeObjects
//                        beforeObjects.add(getBeforeData(serviceClass, queryMethod, value));
//                    }
//                    //记录修改前的数据
//                    for (Object obj : beforeObjects) {
//                        Class<?> beforeClass = obj.getClass();
//                        Field[] fields = beforeClass.getDeclaredFields();
//                        List<Map<String, Object>> list = new ArrayList<>();
//                        for (Field field : fields) {
//                            if (field.isAnnotationPresent(FieldData.class)) {
//                                Map<String, Object> map = new HashMap<>();
//                                field.setAccessible(true);
//                                String name = field.getAnnotation(FieldData.class).name();
//                                String description = field.getAnnotation(FieldData.class).description();
//                                Object vField = field.get(obj);
//
//                                if (StringUtils.isNotEmpty(description)) {
//
//                                    if ("date".equals(description)) {
//                                        Type genericType = field.getGenericType();
//                                        if ("class java.time.LocalDate".equals(genericType.toString())) {
//                                            map.put("name", name);
//                                            map.put("old", vField == null ? "" : vField.toString());
//                                            list.add(map);
//                                            continue;
//                                        }
//
//                                        if ("class java.time.LocalDateTime".equals(genericType.toString())) {
//                                            LocalDateTime dateOld = (LocalDateTime) vField;
//                                            map.put("name", name);
//                                            map.put("old", vField == null ? "" : DateUtil.localDateTimeFormatyMdHms(dateOld));
//                                            list.add(map);
//                                            continue;
//                                        }
//                                    }
//
//                                    Map<String, String> converter = new HashMap<String, String>();
//
//                                    String[] strings = description.split(",");
//                                    for (String string : strings) {
//                                        String[] split = string.split("=");
//                                        converter.put(split[0], split[1]);
//                                    }
//
//                                    map.put("name", name);
//                                    map.put("old", converter.get(vField.toString()));
//                                    list.add(map);
//                                    continue;
//                                }
//
//                                map.put("name", name);
//                                map.put("old", vField);
//                                System.out.println("11" + JSONObject.toJSONString(map));
//                                list.add(map);
//                            }
//                        }
//                        lists.add(list);
//                    }
//                    proceed = joinPoint.proceed();
//                }
//
//                //修改
//                if ("Update".equals(requestMethod)) {
//                    List<JSONObject> params = new ArrayList<>();
//                    for (int i = 0; i < args.length; i++) {
//                        params.add(JSONObject.parseObject(JSONObject.toJSONString(args[i])));
//                    }
//                    //获取修改id
//                    String value = "";
//                    for (int i = 0; i < params.size(); i++) {
//                        JSONObject jsonObject = params.get(i);
//                        value = jsonObject.getString(key);
//                        if (StringUtils.isNotEmpty(value)) {
//                            break;
//                        }
//                    }
//
//                    // 获取修改前的数据放入 beforeObjects
//                    beforeObjects.add(getBeforeData(serviceClass, queryMethod, value));
//                    proceed = joinPoint.proceed();
//                    // 获取修改后的数据
//                    Object afterData = getBeforeData(serviceClass, queryMethod, value);
//                    lists.add(compareTwoClass(beforeObjects.get(0), afterData));
//                }
//
//            } else {   //添加
//                Object obj = args[0];
//                Class<?> insertClass = obj.getClass();
//                Field[] fields = insertClass.getDeclaredFields();
//                List<Map<String, Object>> list = new ArrayList<>();
//                for (Field field : fields) {
//                    Map<String, Object> map = new HashMap<>();
//                    if (field.isAnnotationPresent(FieldData.class) && !"id".equals(field.getName())) {
//                        field.setAccessible(true);
//                        String name = field.getAnnotation(FieldData.class).name();
//                        Object vField = field.get(obj);
//                        String description = field.getAnnotation(FieldData.class).description();
//                        if (StringUtils.isNotEmpty(description)) {
//                            if ("date".equals(description)) {
//                                Type genericType = field.getGenericType();
//                                if ("class java.time.LocalDate".equals(genericType.toString())) {
//                                    map.put("name", name);
//                                    map.put("new", vField == null ? "" : vField.toString());
//                                    list.add(map);
//                                    continue;
//                                }
//
//                                if ("class java.time.LocalDateTime".equals(genericType.toString())) {
//                                    LocalDateTime dateOld = (LocalDateTime) vField;
//                                    map.put("name", name);
//                                    map.put("new", vField == null ? "" : DateUtil.localDateTimeFormatyMdHms(dateOld));
//                                    list.add(map);
//                                    continue;
//                                }
//                            }
//
//                            Map<String, String> converter = new HashMap<String, String>();
//
//                            String[] strings = description.split(",");
//                            for (String string : strings) {
//                                String[] split = string.split("=");
//                                converter.put(split[0], split[1]);
//                            }
//
//                            map.put("name", name);
//                            map.put("new", vField == null ? null : converter.get(vField.toString()));
//                            list.add(map);
//                            continue;
//                        }
//
//                        map.put("name", name);
//                        map.put("new", vField);
//                        list.add(map);
//                    }
//                }
//                lists.add(list);
//                proceed = joinPoint.proceed();
//            }
//
//            if (CollectionUtils.isNotEmpty(lists)) {
//                for (List<Map<String, Object>> bigList : lists) {
//                    if ("1".equals(sysAuditLog.getModel())) {
//                        sysAuditLog.setModelId(loginUser.getUser().getProjectId());
//                    }
//                    sysAuditLog.setOprType(annotation.type());
//                    sysAuditLog.setModel(annotation.module());
//                    sysAuditLog.setReason(annotation.requestDes());
//                    sysAuditLog.setPrId(loginUser.getUser().getProjectId());
//                    sysAuditLog.setOprBy(SecurityUtils.getUsername());
//                    String beforeContent = "";
//                    String afterContent = "";
//
//                    for (Map<String, Object> map : bigList) {
//                        if ("id".equals(map.get("name")))
//                            sysAuditLog.setModelId(Long.valueOf(map.get("old").toString()));
//                        if (ObjectUtils.isNull(map.get("old")) && ObjectUtils.isNull(map.get("new"))) {
//                            continue;
//                        }
//                        beforeContent += map.get("name") + ":" + map.get("old") + "->";
//                        afterContent += map.get("name") + ":" + map.get("new") + "->";
//                    }
//                    if (ObjectUtils.isNotNull(beforeContent)) {
//                        beforeContent = beforeContent.substring(0, beforeContent.length() - 2);
//                    }
//                    if (ObjectUtils.isNotNull(afterContent)) {
//                        afterContent = afterContent.substring(0, afterContent.length() - 2);
//                    }
//
//
//                    if ("Delete".equals(annotation.type())) {
//                        sysAuditLog.setBeforData(beforeContent);
//                        sysAuditLog.setAfterData("");
//                    }
//                    if ("Add".equals((annotation.type()))) {
//                        sysAuditLog.setBeforData("");
//                        sysAuditLog.setAfterData(afterContent);
//                    }
//                    if ("Update".equals((annotation.type()))) {
//                        sysAuditLog.setBeforData(beforeContent);
//                        sysAuditLog.setAfterData(afterContent);
//                    }
//                    sysAuditLogService.add(sysAuditLog);
//                }
//            }
//        } catch (Exception e) {
//            throw new BaseException(e.getMessage());
//        }
//        return proceed;
//    }
//
//
//    public InspectionLogger getAnnotation(ProceedingJoinPoint joinPoint) {
//
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        InspectionLogger annotation = method.getAnnotation(InspectionLogger.class);
//
//        if (ObjectUtils.isNotNull(annotation)) {
//            return annotation;
//        }
//        return null;
//    }
//
//
//    /**
//     * 获取修改前的数据
//     *
//     * @param serviceClass bean名称
//     * @param queryMethod  方法名
//     * @param value        id值
//     * @return
//     */
//    public static Object getBeforeData(String serviceClass, String queryMethod, String value) {
//        Long id = Long.valueOf(value);
//        Method method = ReflectionUtils.findMethod(SpringContextUtil.getBean(serviceClass).getClass(), queryMethod, Long.class);
//        Object obj = ReflectionUtils.invokeMethod(method, SpringContextUtil.getBean(serviceClass), id);
//        return obj;
//    }
//
//
//    /**
//     * 新旧数据对比
//     *
//     * @param oldObject 新数据
//     * @param newObject 老数据
//     * @return
//     * @throws Exception
//     */
//    public static List<Map<String, Object>> compareTwoClass(Object oldObject, Object newObject) throws Exception {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//        Class<?> oldClass = oldObject.getClass();
//        Class<?> newClass = newObject.getClass();
//
//        Field[] oldFields = oldClass.getDeclaredFields();
//        Field[] newFields = newClass.getDeclaredFields();
//
//        for (int i = 0; i < oldFields.length; i++) {
//            if (oldFields[i].isAnnotationPresent(FieldData.class)) {
//                for (int j = 0; j < newFields.length; j++) {
//                    if (oldFields[i].getName().equals(newFields[j].getName())) {
//                        oldFields[i].setAccessible(true);
//                        newFields[j].setAccessible(true);
//                        if (!compareTwoParam(oldFields[i].get(oldObject), newFields[j].get(newObject))) {
//                            FieldData annotation = oldFields[i].getAnnotation(FieldData.class);
//                            Map<String, Object> map = new HashMap<String, Object>();
//
//                            if (StringUtils.isNotEmpty(annotation.description())) {
//
//                                if ("date".equals(annotation.description())) {
//
//                                    Type genericType = oldFields[i].getGenericType();
//                                    if ("class java.time.LocalDate".equals(genericType.toString())) {
//                                        Object dateOld = oldFields[i].get(oldObject);
//                                        Object dateNew = newFields[i].get(newObject);
//                                        map.put("name", annotation.name());
//                                        map.put("old", oldFields[i].get(oldObject) == null ? "" : dateOld.toString());
//                                        map.put("new", dateNew.toString());
//                                        list.add(map);
//                                        continue;
//                                    }
//                                    if ("class java.time.LocalDateTime".equals(genericType.toString())) {
//                                        LocalDateTime dateOld = (LocalDateTime) oldFields[i].get(oldObject);
//                                        LocalDateTime dateNew = (LocalDateTime) newFields[i].get(newObject);
//                                        map.put("name", annotation.name());
//                                        map.put("old", oldFields[i].get(oldObject) == null ? "" : DateUtil.localDateTimeFormatyMdHms(dateOld));
//                                        map.put("new", DateUtil.localDateTimeFormatyMdHms(dateNew));
//                                        list.add(map);
//                                        continue;
//                                    }
//
//                                }
//
//                                Map<String, String> converter = new HashMap<String, String>();
//
//                                String[] strings = annotation.description().split(",");
//                                for (String string : strings) {
//                                    String[] split = string.split("=");
//                                    converter.put(split[0], split[1]);
//                                }
//
//                                map.put("name", annotation.name());
//                                map.put("old", oldFields[i].get(oldObject) == null ? "" : converter.get(oldFields[i].get(oldObject).toString()));
//                                map.put("new", converter.get(newFields[i].get(newObject).toString()));
//                                list.add(map);
//                                continue;
//                            }
//
//
//                            map.put("name", annotation.name());
//                            map.put("old", oldFields[i].get(oldObject) == null ? "" : oldFields[i].get(oldObject));
//                            map.put("new", newFields[j].get(newObject));
//                            if (ObjectUtils.isNotEmpty(map))
//                                list.add(map);
//                        }
//
//                    }
//                }
//            }
//        }
//        return list;
//    }
//
//
//    /**
//     * 数据比较
//     *
//     * @param obj1 旧数据
//     * @param obj2 新数据
//     * @return
//     */
//    public static boolean compareTwoParam(Object obj1, Object obj2) {
//        if (obj1 == null && obj2 == null) {
//            return true;
//        }
//        if (obj1 == null && obj2 != null) {
//            return false;
//        }
//        if (obj1.equals(obj2)) {
//            return true;
//        }
//        return false;
//    }
//
//}
