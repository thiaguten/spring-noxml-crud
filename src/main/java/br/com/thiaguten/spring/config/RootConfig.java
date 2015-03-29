package br.com.thiaguten.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(
		basePackages = { "br.com.thiaguten.spring" }, 
		excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) }
)
@Import(value = { PersistenceConfig.class })
public class RootConfig {

}
