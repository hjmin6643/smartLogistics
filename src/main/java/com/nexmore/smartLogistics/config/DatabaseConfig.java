package com.nexmore.smartLogistics.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Slf4j
@Configuration
@EnableTransactionManagement
@MapperScan(value = {"com.nexmore.smartLogistics.mapper"})
public class DatabaseConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.nexmore.smartLogistics.dto");
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(
                        "classpath:mapper/*.xml"
                )
        );
        sessionFactory.setVfs(SpringBootVFS.class);
        Objects.requireNonNull(sessionFactory.getObject())
                .getConfiguration()
                .setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
