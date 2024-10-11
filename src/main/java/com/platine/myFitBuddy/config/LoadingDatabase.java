package com.platine.myFitBuddy.config;

import com.github.javafaker.Faker;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
import com.platine.myFitBuddy.features.sessions.model.Session;
import com.platine.myFitBuddy.features.sessions.repository.SessionRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoadingDatabase implements CommandLineRunner {

    @Autowired
    private DBUserRepository dbUserRepository;

    @Autowired
    private SessionRepository sampleRepository;

    private final Faker faker = new Faker();

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading database...");
        
        //Users
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        DBUser dbUser = new DBUser("dbuser", passwordEncoder.encode("dbuser"), "USER");
        dbUserRepository.save(dbUser);
        dbUserRepository.save(new DBUser("admin", passwordEncoder.encode("admin"), "ADMIN"));

        //Sessions
        for (int i = 0; i < 10; i++) sampleRepository.save(new Session(faker.name().title(), dbUser));

        log.info("Database loaded.");
    }
}
