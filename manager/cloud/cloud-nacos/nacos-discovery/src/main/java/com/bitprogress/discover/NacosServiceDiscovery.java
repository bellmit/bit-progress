package com.bitprogress.discover;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceInstance;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ListView;
import org.springframework.cloud.client.ServiceInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 不会飞的小鹏
 * 获取Nacos服务
 */
public class NacosServiceDiscovery {

	private NacosDiscoveryProperties discoveryProperties;

	public NacosServiceDiscovery(NacosDiscoveryProperties discoveryProperties) {
		this.discoveryProperties = discoveryProperties;
	}

	/**
	 * Return all instances for the given service.
	 * @param serviceId id of service
	 * @return list of instances
	 * @throws NacosException nacosException
	 */
	public List<ServiceInstance> getInstances(String serviceId) throws NacosException {
		String group = discoveryProperties.getGroup();
		List<Instance> instances = discoveryProperties.namingServiceInstance()
				.selectInstances(serviceId, group, true);
		return hostToServiceInstanceList(instances, serviceId);
	}

	/**
	 * Return the names of all services.
	 * @return list of service names
	 * @throws NacosException nacosException
	 */
	public List<String> getServices() throws NacosException {
		String group = discoveryProperties.getGroup();
		ListView<String> services = discoveryProperties.namingServiceInstance()
				.getServicesOfServer(1, Integer.MAX_VALUE, group);
		return services.getData();
	}

	public static List<ServiceInstance> hostToServiceInstanceList(List<Instance> instances, String serviceId) {
		List<ServiceInstance> result = new ArrayList<>(instances.size());
		for (Instance instance : instances) {
			ServiceInstance serviceInstance = hostToServiceInstance(instance, serviceId);
			if (serviceInstance != null) {
				result.add(serviceInstance);
			}
		}
		return result;
	}

	public static ServiceInstance hostToServiceInstance(Instance instance, String serviceId) {
		if (instance == null || !instance.isEnabled() || !instance.isHealthy()) {
			return null;
		}
		NacosServiceInstance nacosServiceInstance = new NacosServiceInstance();
		nacosServiceInstance.setHost(instance.getIp());
		nacosServiceInstance.setPort(instance.getPort());
		nacosServiceInstance.setServiceId(serviceId);

		Map<String, String> metadata = new HashMap<>();
		metadata.put("nacos.instanceId", instance.getInstanceId());
		metadata.put("nacos.weight", instance.getWeight() + "");
		metadata.put("nacos.healthy", instance.isHealthy() + "");
		metadata.put("nacos.cluster", instance.getClusterName() + "");
		metadata.putAll(instance.getMetadata());
		nacosServiceInstance.setMetadata(metadata);

		if (metadata.containsKey("secure")) {
			boolean secure = Boolean.parseBoolean(metadata.get("secure"));
			nacosServiceInstance.setSecure(secure);
		}
		return nacosServiceInstance;
	}

}
