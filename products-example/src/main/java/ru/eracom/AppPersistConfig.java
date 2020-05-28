package ru.eracom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.eracom.persist.repo")
public class AppPersistConfig {

    @Bean(name="dataSource")
    public DataSource getDataSource() {
        // Создаем источник данных
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Задаем параметры подключения к базе данных
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_mvc_db_les4?serverTimezone=Europe/Moscow&useSSL=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("my18Erasql");
        return dataSource;
    }

    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        // Создаем класса фабрики, реализующей интерфейс
        // FactoryBean<EntityManagerFactory>
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        // Задаем источник подключения
        factory.setDataSource(getDataSource());

        // Задаем адаптер для конкретной реализации JPA,
        // указывает, какая именно библиотека будет использоваться в качестве
        // поставщика постоянства
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Указание пакета, в котором будут находиться классы-сущности
        factory.setPackagesToScan("ru.eracom.persist.entity");

        // Создание свойств для настройки Hibernate
        factory.setJpaProperties(jpaProperties());
        return factory;
    }
    @Bean
    public Properties jpaProperties() {
        // Создание свойств для настройки Hibernate
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        // Указание диалекта конкретной базы данных
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        // Указание максимальной глубины связи
        jpaProperties.put("hibernate.max_fetch_depth", 3);

        // Максимальное количество строк, возвращаемых за один запрос из БД
        jpaProperties.put("hibernate.jdbc.fetch_size", 50);

        // Максимальное количество запросов при использовании пакетных операций
        jpaProperties.put("hibernate.jdbc.batch_size", 10);

        // Включает логирование
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.format_sql", true);
        return jpaProperties;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        // Создание менеджера транзакций
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);
        return tm;
    }
}




