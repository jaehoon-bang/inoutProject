package kr.maxted.tamtam.core.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
public class databaseConfig {

	private static final String DEFAULT_NAMING_STRATEGY
	 = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.api.datasource")
	public DataSource coreDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.api2.datasource")
	public DataSource apiDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.admin.datasource")
	public DataSource adminDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name = "entiyManagerFactoryCore")
	public LocalContainerEntityManagerFactoryBean coreEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		Map<String, String> propertiesHashMap = new HashMap<String, String>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);
		
		return builder.dataSource(coreDataSource())
				.packages("kr.maxted.tamtam.core")
				.properties(propertiesHashMap)
				.build();
	}
	
	@Bean(name = "entiyManagerFactoryApi")
	public LocalContainerEntityManagerFactoryBean apiEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		Map<String, String> propertiesHashMap = new HashMap<String, String>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);
		
		return builder.dataSource(apiDataSource())
				.packages("kr.maxted.tamtam.api")
				.properties(propertiesHashMap)
				.build();
	}
	
	@Bean(name = "entiyManagerFactoryAdmin")
	public LocalContainerEntityManagerFactoryBean adminEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		Map<String, String> propertiesHashMap = new HashMap<String, String>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);
		
		return builder.dataSource(adminDataSource())
				.packages("kr.maxted.tamtam.admin")
				.properties(propertiesHashMap)
				.build();
	}
	
	@Primary
	@Bean(name = "transactionManagerCore")
	PlatformTransactionManager coreTransactionManager(
			EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(coreEntityManagerFactory(builder).getObject());
	}
	
	@Bean(name = "transactionManagerApi")
	PlatformTransactionManager apiTransactionManager(
			EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(apiEntityManagerFactory(builder).getObject());
	}
	
	@Bean(name = "transactionManagerAdmin")
	PlatformTransactionManager adminTransactionManager(
			EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(adminEntityManagerFactory(builder).getObject());
	}
	
	@Primary
	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(
			basePackages = "kr.maxted.tamtam.core",
			entityManagerFactoryRef = "entiyManagerFactoryCore",
			transactionManagerRef = "transactionManagerCore"
			)
	static class DBCoreJpaRepositoriesConfig{
		
	}
	
	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(
			basePackages = "kr.maxted.tamtam.api",
			entityManagerFactoryRef = "entiyManagerFactoryApi",
			transactionManagerRef = "transactionManagerApi"
			)
	static class DBApiJpaRepositoriesConfig{
		
	}
	
	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(
			basePackages = "kr.maxted.tamtam.admin",
			entityManagerFactoryRef = "entiyManagerFactoryAdmin",
			transactionManagerRef = "transactionManagerAdmin"
			)
	static class DBAdminJpaRepositoriesConfig{
		
	}
}
