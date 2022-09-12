package spring.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import spring.proxy.config.AppV1Config;
import spring.proxy.config.AppV2Config;
import spring.proxy.config.v1_proxy.ConcreteProxyConfig;
import spring.proxy.config.v1_proxy.InterfaceProxyConfig;
import spring.proxy.config.v2_dinamicproxy.DinamicProxyBasicConfig;
import spring.proxy.config.v3_proxyfactory.ProxyFactoryConfigV1;
import spring.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2;
import spring.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
import spring.proxy.config.v5_autoproxy.AutoProxyConfig;

//@Import(AppV1Config.class)
//@Import({AppV1Config.class, AppV2Config.class})
//@Import(InterfaceProxyConfig.class)
//@Import(ConcreteProxyConfig.class)
//@Import(DinamicProxyBasicConfig.class)
//@Import(ProxyFactoryConfigV1.class)
//@Import(ProxyFactoryConfigV2.class)
//@Import(BeanPostProcessorConfig.class)
@Import(AutoProxyConfig.class)
@SpringBootApplication(scanBasePackages = "spring.proxy.app")
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
