//package com.randing.framework.aspectj;
//
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SpringContextUtil implements ApplicationContextAware {
//
//    private static ApplicationContext  applicationContext;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext){
//        SpringContextUtil.applicationContext = applicationContext;
//    }
//
//
//    public static ApplicationContext getApplicationContext(){
//        return applicationContext;
//    }
//
//      /**
//      * 获取对象
//      * @return  Object 一个以所给名字注册的bean的实例
//     */
//      public static Object getBean(String name) throws BeansException{
//            return applicationContext.getBean(name);
//      }
//
//}
