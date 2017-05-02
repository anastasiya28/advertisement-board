package myProjectTest;

import myProject.repository.impls.AdvertisementDAOImpl;
import myProject.repository.interfaces.AdvertisementDAO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "myProjectTest")
@PropertySource(value = {"classpath:application.properties"})
public class TestConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("datasource.driver"));
        dataSource.setUrl(env.getRequiredProperty("datasource.url"));
        dataSource.setUsername(env.getRequiredProperty("datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("datasource.password"));
        return dataSource;
    }

    @Value("classpath:sql/schema.sql")
    private Resource schemaScript;

    @Value("classpath:sql/data.sql")
    private Resource dataScript;

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        populator.addScript(dataScript);
        return populator;
    }

    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty(AvailableSettings.DIALECT, env.getRequiredProperty("hibernate.dialect"));
                setProperty(AvailableSettings.GLOBALLY_QUOTED_IDENTIFIERS, env.getRequiredProperty("hibernate.globally_quoted_identifiers"));
                setProperty(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, env.getProperty("hibernate.enable_lazy_load_no_trans"));
                setProperty(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.show_sql", "false"));
                setProperty(AvailableSettings.FORMAT_SQL, env.getProperty("hibernate.format_sql", "false"));
            }
        };
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan("myProject.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    @Bean
    public AdvertisementDAO getAdvertisementDAO() {
        return new AdvertisementDAOImpl();
    }

//    @Bean
//    public CommentDAO getCommentDAO() {
//        return new CommentDAOImpl();
//    }
}
