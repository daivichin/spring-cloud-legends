package com.legends.cloud.web.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.legends.cloud.web.config.properties.LegendsBaseProperties;
import com.legends.cloud.web.service.LegendBaseConfigService;

@Configuration
@EnableConfigurationProperties(LegendsBaseProperties.class)
@ConditionalOnClass(LegendBaseConfigService.class)
@ConditionalOnProperty(prefix = "legends", value = "enabled", matchIfMissing = true)
@EnableApolloConfig
public class LegendsBaseAutoConfiguration {

    @Autowired
    private LegendsBaseProperties legendsBaseProperties;

    @Bean
    @ConditionalOnMissingBean(LegendBaseConfigService.class)
    public LegendBaseConfigService legendBaseConfigService() {
        return legendBaseConfigFactory();
    }

    private LegendBaseConfigService legendBaseConfigFactory() {
        LegendBaseConfigService legendBaseConfigService = new LegendBaseConfigService();
        legendBaseConfigService.setSwBasePack(legendsBaseProperties.getSwBasePack());
        legendBaseConfigService.setSwDesc(legendsBaseProperties.getSwDesc());
        legendBaseConfigService.setSwBasePacks(legendsBaseProperties.getSwBasePacks());
        legendBaseConfigService.setSwEmail(legendsBaseProperties.getSwEmail());
        legendBaseConfigService.setSwName(legendsBaseProperties.getSwName());
        legendBaseConfigService.setSwTitle(legendsBaseProperties.getSwTitle());
        legendBaseConfigService.setSwUrl(legendsBaseProperties.getSwUrl());
        legendBaseConfigService.setSwVers(legendsBaseProperties.getSwVers());
        legendBaseConfigService.setSecWhiteList(legendsBaseProperties.getSecWhiteList());
        return legendBaseConfigService;
    }
}
