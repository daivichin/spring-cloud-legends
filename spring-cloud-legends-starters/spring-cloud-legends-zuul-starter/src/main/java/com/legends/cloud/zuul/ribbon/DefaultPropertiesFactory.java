package com.legends.cloud.zuul.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ServerListFilter;
import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author herion
 * @Description
 * @Date  2019/1/29
 * @Param 
 * @return 
 **/
public class DefaultPropertiesFactory extends PropertiesFactory {
    private static final String NAMESPACE = "ribbon";
    @Autowired
    private Environment environment;

    private Map<Class, String> classToProperty = new HashMap<>();

    public DefaultPropertiesFactory() {
        super();
        classToProperty.put(ILoadBalancer.class, "NFLoadBalancerClassName");
        classToProperty.put(IPing.class, "NFLoadBalancerPingClassName");
        classToProperty.put(IRule.class, "NFLoadBalancerRuleClassName");
        classToProperty.put(ServerList.class, "NIWSServerListClassName");
        classToProperty.put(ServerListFilter.class, "NIWSServerListFilterClassName");
    }

    @Override
    public String getClassName(Class clazz, String name) {
        String className = super.getClassName(clazz, name);
        // 读取全局配置
        if(!StringUtils.hasText(className) && this.classToProperty.containsKey(clazz)){
            String classNameProperty = this.classToProperty.get(clazz);
            className = environment.getProperty(NAMESPACE + "." + classNameProperty);
        }
        return className;
    }

    public static String getClientConfig(IClientConfig clientConfig, String key){
        String value = (String) clientConfig.getProperties().get(key);
        // 读取全局配置
        if(!StringUtils.hasText(value)){
            Configuration subset = ConfigurationManager.getConfigInstance().subset(clientConfig.getNameSpace());
            value = subset.getString(key);
        }

        return value;
    }
}
