package framwork.monitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 微服务监控
 * 
 * @author S.J.
 * @date 2018/08/10
 */
@RestController
public class ServiceDiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String springApplicationName;

    /**
     * @Title: showInstanceInfo
     * @Description: 本地服务实例的信息
     * @return ServiceInstance
     */
    @GetMapping("/instance-info")
    public ServiceInstance showInstanceInfo() {
        List<ServiceInstance> list = discoveryClient.getInstances(springApplicationName);
        if (list != null && list.size() > 0 ) {
            return list.get(0);
        }
        return null;
    }

}
