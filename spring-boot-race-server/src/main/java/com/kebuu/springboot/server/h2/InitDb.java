package com.kebuu.springboot.server.h2;

import com.kebuu.springboot.server.config.StepConfig;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitDb {

    @Autowired
    private StepConfig stepConfig;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void initDb() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:h2:tcp://localhost:9090/~/spring-boot-step2");
        dataSource.setUsername("sa");
        dataSource.setPassword("ctardella");
        dataSource.setDriverClassName("org.h2.Driver");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("DROP ALL OBJECTS;");
        jdbcTemplate.execute("CREATE TABLE spring_boot_step_2 (secret VARCHAR);");
        jdbcTemplate.execute("INSERT INTO spring_boot_step_2 VALUES ('" + stepConfig.getStep2() + "');");
        jdbcTemplate.execute("CREATE USER zenika PASSWORD 'technozaure';");
        jdbcTemplate.execute("GRANT SELECT ON SPRING_BOOT_STEP_2 TO zenika;");
        jdbcTemplate.execute("ALTER USER SA SET PASSWORD '" + stepConfig.getH2Pass() + "';");
    }
}