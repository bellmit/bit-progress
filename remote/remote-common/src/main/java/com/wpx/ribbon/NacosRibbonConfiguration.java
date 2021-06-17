package com.wpx.ribbon;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.ConditionalOnRibbonNacos;
import com.alibaba.cloud.nacos.ribbon.NacosServerIntrospector;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ServerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(SpringClientFactory.class)
@ConditionalOnRibbonNacos
public class NacosRibbonConfiguration {

	@Autowired
	private PropertiesFactory propertiesFactory;

	@Bean
	@ConditionalOnMissingBean
	public ServerList<?> ribbonServerList(IClientConfig config, NacosDiscoveryProperties nacosDiscoveryProperties) {
		if (propertiesFactory.isSet(ServerList.class, config.getClientName())) {
			ServerList serverList = propertiesFactory.get(ServerList.class, config, config.getClientName());
			return serverList;
		}
		NacosServerList serverList = new NacosServerList(nacosDiscoveryProperties);
		serverList.initWithNiwsConfig(config);
		return serverList;
	}

	@Bean
	@ConditionalOnMissingBean
	public NacosServerIntrospector nacosServerIntrospector() {
		return new NacosServerIntrospector();
	}

}
