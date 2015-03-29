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
		em.setJpaProperties(vendorAdapterProperties());
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	private Properties vendorAdapterProperties() {
		Properties properties = new Properties();
		// hibernate
		properties.setProperty("hibernate.dialect", org.hibernate.dialect.HSQLDialect.class.getName());
		properties.setProperty("hibernate.show_sql", "false");
		properties.setProperty("hibernate.format_sql", "false");
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		// hikaricp
		properties.setProperty("hibernate.connection.provider_class", com.zaxxer.hikari.hibernate.HikariConnectionProvider.class.getName());
		properties.setProperty("hibernate.hikari.maximumPoolSize", "100");
		properties.setProperty("hibernate.hikari.idleTimeout", "30000");
		properties.setProperty("hibernate.hikari.connectionTestQuery", "SELECT * FROM INFORMATION_SCHEMA.SYSTEM_TABLES");
		properties.setProperty("hibernate.hikari.dataSourceClassName", org.hsqldb.jdbc.JDBCDataSource.class.getName());
		properties.setProperty("hibernate.hikari.dataSource.url", "jdbc:hsqldb:mem:dbteste");
		properties.setProperty("hibernate.hikari.dataSource.user", "SA");
		properties.setProperty("hibernate.hikari.dataSource.password", "");
		return properties;
	}
}
