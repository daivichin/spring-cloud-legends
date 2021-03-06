package com.legends.cloud.gateway.config;

import com.legends.cloud.gateway.filter.SwaggerHeaderFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * swagger 聚合过滤器自动装配
 * @Description
 * @Author herion
 * @Date 2020-08-13 17:49
 */
@Configuration(proxyBeanMethods = false)
public class SwaggerAutoConfig {

    @Bean
    @ConditionalOnMissingBean
	public SwaggerHeaderFilter SwaggerHeaderFilter(){
		System.out.println("我初始化了");
		return new SwaggerHeaderFilter();
	}
}