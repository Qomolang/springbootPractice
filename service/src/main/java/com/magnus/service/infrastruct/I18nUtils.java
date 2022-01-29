package com.magnus.service.infrastruct;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 国际化工具类
 */
@Component
public class I18nUtils {

    /**
     * springboot自动配置
     */
    @Resource
    MessageSource messageSource;


    /**
     * 获取单个国际化翻译值
     */
    public  String get(String msgKey) {

        return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());

    }
}