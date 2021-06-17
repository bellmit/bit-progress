package com.wpx.ribbon;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerList;
import com.wpx.common.util.StringUtils;
import com.wpx.constant.NacosConstant;
import com.wpx.property.NacosVersionProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class NacosServerList extends AbstractServerList<NacosServer> {

    @Autowired
    private NacosVersionProperties nacosVersionProperties;

    private NacosDiscoveryProperties discoveryProperties;

    private String serviceId;

    public NacosServerList(NacosDiscoveryProperties discoveryProperties) {
        this.discoveryProperties = discoveryProperties;
    }

    @Override
    public List<NacosServer> getInitialListOfServers() {
        return getServers();
    }

    @Override
    public List<NacosServer> getUpdatedListOfServers() {
        return getServers();
    }

    private List<NacosServer> getServers() {
        try {
            String group = discoveryProperties.getGroup();
            List<Instance> instances = discoveryProperties.namingServiceInstance()
                    .selectInstances(serviceId, group, true);
            return instancesToServerList(instances, serviceId);
        }
        catch (Exception e) {
            throw new IllegalStateException(
                    "Can not get service instances from nacos, serviceId=" + serviceId,
                    e);
        }
    }

    private List<NacosServer> instancesToServerList(List<Instance> instances, String serviceId) {
        List<NacosServer> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(instances)) {
            return result;
        }
        String version = nacosVersionProperties.getVersionByServiceId(serviceId);
        for (Instance instance : instances) {
            String instanceVersion = instance.getMetadata().get(NacosConstant.VERSION);
            if (StringUtils.equals(version, instanceVersion)) {
                result.add(new NacosServer(instance));
            }
        }

        return result;
    }

    public String getServiceId() {
        return serviceId;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        this.serviceId = iClientConfig.getClientName();
    }

}
