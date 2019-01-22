package com.stackroute.music.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@PropertySource(value = "classpath:application.properties")
@Slf4j
public class PopulateDatabaseUsingApplicationRunner implements ApplicationRunner {
    @Value("${spring.datasource.url}")
    private String h2DatabaseUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String h2Driver;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void run(ApplicationArguments args) {
        try {
            Connection connection = DriverManager.getConnection(h2DatabaseUrl, username, password);
            Statement statement = connection.createStatement();
            log.info("Reading SQL File after application start up");
            String sqlQuery = new String(Files.readAllBytes(Paths.get("/home/user/Downloads/music/src/main/resources/H2PrefillUsingApplicationRunner")));
            statement.execute(sqlQuery);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
