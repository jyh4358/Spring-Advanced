package spring.proxy.config.v2_dinamicproxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.proxy.app.v1.*;
import spring.proxy.config.v2_dinamicproxy.handler.LogTracFilterHandler;
import spring.proxy.config.v2_dinamicproxy.handler.LogTraceBasicHandler;
import spring.proxy.trace.logtrace.LogTrace;
import spring.proxy.trace.logtrace.ThreadLocalLogTrace;

import java.lang.reflect.Proxy;

@Configuration
public class DinamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1Impl orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));

        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(
                OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTracFilterHandler(orderControllerV1, logTrace, PATTERNS));

        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1Impl orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(
                OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTracFilterHandler(orderServiceV1, logTrace, PATTERNS));

        return proxy;
    }


    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();

        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(
                OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTracFilterHandler(orderRepositoryV1, logTrace, PATTERNS));
        return proxy;
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
