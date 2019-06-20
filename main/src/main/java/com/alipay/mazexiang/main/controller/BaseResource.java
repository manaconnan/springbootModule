/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.main.controller;

import com.alipay.mazexiang.service.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 *
 * @author mazexiang
 * @version $Id: BaseResource.java, v 0.1 2019年02月28日 20:56 mazexiang Exp $
 */
@Slf4j
@Service
public  class BaseResource implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    @Autowired
    private TestBean testBean;

    public BaseResource(ApplicationContext applicationContext){
        BaseResource. applicationContext = applicationContext;
        this.injectBeans();
    }
    public BaseResource(){
        this.injectBeans();
    }

    public String test(String msg){
        return testBean.sayHello(msg);
    }
    private void injectBeans() {

        if (applicationContext == null){
            return;
        }

        //if (this.getClass().isAnnotationPresent(org.springframework.stereotype.Service.class)
        //    || this.getClass().isAnnotationPresent(org.springframework.stereotype.Controller.class)
        //    || this.getClass().isAnnotationPresent(org.springframework.stereotype.Component.class) ){
        //    return;
        //}

        Class clazz = this.getClass();
        do {
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(org.springframework.beans.factory.annotation.Autowired.class)
                    || f.isAnnotationPresent(javax.annotation.Resource.class)){

                    try {
                        String simpleName = f.getType().getSimpleName();
                        String beanName = StringUtils.uncapitalize(simpleName);

                        Object bean = applicationContext.getBean(beanName);
                        if (bean == null){
                            return;
                        }

                        boolean accessible = f.isAccessible();
                        f.setAccessible(true);
                        f.set(this,bean);
                        f.setAccessible(accessible);
                    }catch (Exception e){
                        log.error(clazz.getName() + "当new对象注入类" + f.getName() + "的时候，发生了错误",e);
                        e.printStackTrace();
                    }

                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BaseResource.applicationContext = applicationContext;
    }
}