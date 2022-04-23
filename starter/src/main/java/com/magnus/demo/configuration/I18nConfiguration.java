package com.magnus.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author 84028
 */
@Configuration
public class I18nConfiguration {
    /**
     * 配合I18n
     * 用SessionLocaleResolver替换spring默认解析器
     * spring默认LocaleResolver解析器为AcceptHeaderLocaleResolver，不允许重新设置locale，setLocale()直接抛出异常
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }

    /**
     *  注册此拦截器，则根据query参数确定语言环境
     *  不注册这个拦截器，则根据前端header中Accept-Language来确定语言环境
     * @return
     */
    @Bean
    public LocaleChangeInterceptor i18nInterceptor(){
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        //默认参数名为locale 改为更常见的lang
        localeInterceptor.setParamName("lang");
        return localeInterceptor;
    }

}
