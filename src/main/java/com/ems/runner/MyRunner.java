package com.ems.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // --- Create user 'mario' only if they don't exist ---
        String marioUsername = "mario";
        // Check if the user exists by counting them
        Integer userCountMario = jdbcTemplate.queryForObject("SELECT count(*) FROM users WHERE username = ?", Integer.class, marioUsername);

        if (userCountMario == 0) {
            String marioPassword = encoder.encode("123456");
            jdbcTemplate.update("INSERT INTO users (username, password, enabled) VALUES (?,?,?)", marioUsername, marioPassword, true);
            jdbcTemplate.update("INSERT INTO authorities (username, authority) VALUES (?,?)", marioUsername, "ROLE_MANAGER");
            System.out.println(">>> User 'mario' created!");
        } else {
            System.out.println(">>> User 'mario' already exists.");
        }


        // --- Create user 'mickey' only if they don't exist ---
        String mickeyUsername = "mickey";
        Integer userCountMickey = jdbcTemplate.queryForObject("SELECT count(*) FROM users WHERE username = ?", Integer.class, mickeyUsername);

        if (userCountMickey == 0) {
            String mickeyPassword = encoder.encode("mickey@123");
            jdbcTemplate.update("INSERT INTO users (username, password, enabled) VALUES (?,?,?)", mickeyUsername, mickeyPassword, true);
            jdbcTemplate.update("INSERT INTO authorities (username, authority) VALUES (?,?)", mickeyUsername, "ROLE_USER");
            System.out.println(">>> User 'mickey' created!");
        } else {
            System.out.println(">>> User 'mickey' already exists.");
        }
    }
}