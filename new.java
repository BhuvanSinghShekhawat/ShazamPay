package com.xero.SwiftPay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MyApplication {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/")
    public String home(@RequestParam(value = "request", required = false) String requestParam) {
        if (requestParam != null) {
            try (Connection connection = dataSource.getConnection()) {
                String sql = "INSERT INTO test (request) VALUES (?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, requestParam);
                statement.executeUpdate();
                                System.out.println("Request saved: " + requestParam);
                return "Request saved: " + requestParam;
            } catch (SQLException e) {
                e.printStackTrace();
                                System.out.println("Error saving request");
                return "Error saving request";
            }
        }
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}