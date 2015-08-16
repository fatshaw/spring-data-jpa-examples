package fatshaw.experiment.jpa.context;

import com.google.common.collect.Maps;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by xiaochaojie on 15/8/16.
 */

@Configuration
@ComponentScan("fatshaw.experiment.jpa.sample")
@PropertySource("classpath:jdbc.properties")
@EnableJpaRepositories("fatshaw.experiment.jpa.sample")
@EnableTransactionManagement
public class JPAContext {


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    class DataSourceConfig {

        @Value("${jdbc.mysql.driverClassName}")
        public String driverClassName;

        @Value("${jdbc.mysql.url}")
        public String url;

        @Value("${jdbc.mysql.username}")
        public String username;

        @Value("${jdbc.mysql.password}")
        public String password;

    }


    @Bean
    public DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig();
    }

    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(dataSourceConfig().driverClassName);
        dataSource.setUrl(dataSourceConfig().url);
        dataSource.setUsername(dataSourceConfig().username);
        dataSource.setPassword(dataSourceConfig().password);
        dataSource.setMaxTotal(20);
        dataSource.setMaxIdle(8);
        dataSource.setMinIdle(8);
        dataSource.setMaxWaitMillis(5000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setValidationQuery("select sysdate()");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        Map<String, Object> jpaProperties = Maps.newHashMap();
        jpaProperties.put("hibernate.jdbc.fetch_size", 50);
        jpaProperties.put("hibernate.jdbc.batch_size", 30);
        jpaProperties.put("hibernate.show_sql", true);
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaPropertyMap(jpaProperties);
        factory.setPackagesToScan("fatshaw.experiment.jpa.sample");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory;
    }
}