package com.platine.myFitBuddy.config;

import com.github.javafaker.Faker;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import com.platine.myFitBuddy.features.exercices.repository.ExerciseRepository;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.repository.SessionContentRepository;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import com.platine.myFitBuddy.features.sessions.repository.SessionRepository;
import com.platine.myFitBuddy.utils.ExercisesFactory;
import java.util.Arrays;
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
  private ExerciseRepository exerciseRepository;

  @Autowired
  private SessionRepository sessionRepository;

  @Autowired
  private SessionContentRepository sessionContentRepository;

  private final Faker faker = new Faker();

  @Override
  public void run(String... args) {
    log.info("Loading database...");
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    DBUser dbuser = dbUserRepository.save(
      new DBUser(
        "dbuser",
        "dbuser@univ-lille.fr",
        passwordEncoder.encode("dbuser"),
        "USER"
      )
    );

    dbUserRepository.save(
      new DBUser(
        "admin",
        "myfitadmin@univ-lille.fr",
        passwordEncoder.encode("admin"),
        "ADMIN"
      )
    );

    // Exercises
    Exercise[] exercices = ExercisesFactory.loadExercises();
    exerciseRepository.saveAll(Arrays.asList(exercices));

    // Sessions
    for (int i = 0; i < 10; i++) {
      sessionRepository.save(new FitSession(faker.name().title(), dbuser));
    }
    FitSession dummySession = sessionRepository.save(
      new FitSession(faker.name().title(), dbuser)
    );
    Exercise dummyExercise = exerciseRepository.save(
      new Exercise("TEST", MuscleGroup.SHOULDERS, "EnTesting", "FrTest")
    );

    sessionContentRepository.save(
      new SessionContent(dummySession.getId(), dummyExercise.getId(), 5, 120, 1, dbuser)
    );
    sessionContentRepository.save(
      new SessionContent(dummySession.getId(), dummyExercise.getId(), 2, 60, 2, dbuser)
    );
    sessionContentRepository.save(
      new SessionContent(dummySession.getId(), dummyExercise.getId(), 3, 90, 3, dbuser)
    );

    log.info("Database loaded.");
  }
}
