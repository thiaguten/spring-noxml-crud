package br.com.thiaguten.spring.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setPackagesToScan("br.com.thiaguten.spring.model");
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		// hibernate-hikaricp connection pool
		properties.setProperty("hibernate.connection.provider_class", com.zaxxer.hikari.hibernate.HikariConnectionProvider.class.getName());
		properties.setProperty("hibernate.hikari.maximumPoolSize", "100");
		properties.setProperty("hibernate.hikari.idleTimeout", "30000");
		// h2 database
		properties.setProperty("hibernate.dialect", org.hibernate.dialect.H2Dialect.class.getName());
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		properties.setProperty("hibernate.hikari.connectionTestQuery", "SELECT 1");
		properties.setProperty("hibernate.hikari.dataSourceClassName", org.h2.jdbcx.JdbcDataSource.class.getName());
		properties.setProperty("hibernate.hikari.dataSource.url", "jdbc:h2:./target/h2db");
		properties.setProperty("hibernate.hikari.dataSource.user", "sa");
		properties.setProperty("hibernate.hikari.dataSource.password", "sa");
		return properties;
	}
}
