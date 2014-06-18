package com.kebuu.springboot.server.h2;

import org.h2.tools.Server;

import java.sql.SQLException;

public class H2Server {

    public static void main(String[] args) throws SQLException {
        Server server = Server.createTcpServer("-tcpPort", "9090", "-tcpPassword", "password", "-tcpAllowOthers").start();
    }
//    jdbc:h2:tcp://localhost:9090/~/spring-boot-step2
//    CREATE TABLE spring_boot_step_2 (secret VARCHAR);
//    INSERT INTO spring_boot_step_2 VALUES ('secret message');
//    CREATE USER zenika PASSWORD 'technozaure';
//    GRANT SELECT ON SPRING_BOOT_STEP_2 TO zenika;
//    ALTER USER SA SET PASSWORD 'ctardella';
}
