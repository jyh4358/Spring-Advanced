package spring.proxy.config.v1_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.proxy.app.v2.OrderControllerV2;
import spring.proxy.app.v2.OrderRepositoryV2;
import spring.proxy.app.v2.OrderServiceV2;
import spring.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import spring.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import spring.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import spring.proxy.trace.logtrace.LogTrace;
import spring.proxy.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        OrderRepositoryV2 repository = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(repository, logTrace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        OrderServiceV2 service = new OrderServiceV2(orderRepositoryV2(logTrace));
        return new OrderServiceConcreteProxy(service, logTrace);
    }

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
        OrderControllerV2 controller = new OrderControllerV2(orderServiceV2(logTrace));
        return new OrderControllerConcreteProxy(controller, logTrace);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

}
