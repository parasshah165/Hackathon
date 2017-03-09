package com.tcs.predix.beta.time.series.ingestion;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.context.support.StandardServletEnvironment;




/**
 * 
 * @author predix -
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ge.predix.solsvc.bootstrap.tsb.client","com.ge.predix.solsvc","com.ge.predix.solsvc.bootstrap.tsb","com.tcs.predix.beta.time.series.ingestion"})
@PropertySource("classpath:application-default.properties")
public class CargoMonitoringBoot {
	private static Logger log = LoggerFactory.getLogger(CargoMonitoringBoot.class);
    /**
     * @param args -
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CargoMonitoringBoot.class);
        ConfigurableApplicationContext ctx = app.run(args);

		log.info("Let's inspect the properties provided by Spring Boot:");
		MutablePropertySources propertySources = ((StandardServletEnvironment) ctx
				.getEnvironment()).getPropertySources();
		Iterator<org.springframework.core.env.PropertySource<?>> iterator = propertySources
				.iterator();
		while (iterator.hasNext()) {
			Object propertySourceObject = iterator.next();
			if (propertySourceObject instanceof org.springframework.core.env.PropertySource) {
				org.springframework.core.env.PropertySource<?> propertySource = (org.springframework.core.env.PropertySource<?>) propertySourceObject;
				log.info("propertySource=" + propertySource.getName()
						+ " values=" + propertySource.getSource() + "class="
						+ propertySource.getClass());
			}
		}

		log.info("Let's inspect the profiles provided by Spring Boot:");
		String profiles[] = ctx.getEnvironment().getActiveProfiles();
		for (int i = 0; i < profiles.length; i++)
			log.info("profile=" + profiles[i]);
    }
    /**
     * @return -
     */
    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
        return new TomcatEmbeddedServletContainerFactory();
    }
    

}
