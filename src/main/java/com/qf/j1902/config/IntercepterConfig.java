package com.qf.j1902.config;

import com.qf.j1902.intercepter.ZhongCintercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by Qxk on 2019/6/9.
 */

public class IntercepterConfig extends WebMvcConfigurationSupport {
    @Bean
    public ZhongCintercepter zhongCintercepter(){
        return new ZhongCintercepter();
    }
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        ZhongCintercepter zhongCintercepter=zhongCintercepter();
            registry.addInterceptor(zhongCintercepter).excludePathPatterns()
                    .addPathPatterns("http://localhost:8080/main");
        super.addInterceptors(registry);
    }
}
