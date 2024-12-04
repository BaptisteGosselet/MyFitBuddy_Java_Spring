package com.platine.myFitBuddy.config;

import com.github.javafaker.Faker;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import com.platine.myFitBuddy.features.exercices.repository.ExerciseRepository;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitRecords.repository.FitRecordRepository;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.repository.FitSetRepository;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.repository.SessionContentRepository;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import com.platine.myFitBuddy.features.sessions.repository.SessionRepository;
import com.platine.myFitBuddy.utils.ExercisesFactory;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
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

  @Autowired
  private FitRecordRepository fitRecordRepository;

  @Autowired
  private FitSetRepository fitSetRepository;

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
    List<Exercise> savedExercises = exerciseRepository.saveAll(
      Arrays.asList(ExercisesFactory.loadExercises())
    );

    // Sessions
    FitSession sessionA = sessionRepository.save(new FitSession("Session A", dbuser));
    FitSession sessionB = sessionRepository.save(new FitSession("Session B", dbuser));
    FitSession sessionC = sessionRepository.save(new FitSession("Session C", dbuser));

    sessionContentRepository.save(
      new SessionContent(
        sessionA.getId(),
        savedExercises.get(0).getId(),
        3,
        150,
        1,
        dbuser
      )
    );

    sessionContentRepository.save(
      new SessionContent(
        sessionA.getId(),
        savedExercises.get(1).getId(),
        3,
        100,
        2,
        dbuser
      )
    );

    sessionContentRepository.save(
      new SessionContent(
        sessionA.getId(),
        savedExercises.get(2).getId(),
        4,
        50,
        3,
        dbuser
      )
    );
    sessionContentRepository.save(
      new SessionContent(
        sessionB.getId(),
        savedExercises.get(3).getId(),
        3,
        150,
        1,
        dbuser
      )
    );

    sessionContentRepository.save(
      new SessionContent(
        sessionB.getId(),
        savedExercises.get(4).getId(),
        3,
        100,
        2,
        dbuser
      )
    );

    sessionContentRepository.save(
      new SessionContent(
        sessionB.getId(),
        savedExercises.get(5).getId(),
        4,
        50,
        3,
        dbuser
      )
    );

    sessionContentRepository.save(
      new SessionContent(
        sessionC.getId(),
        savedExercises.get(6).getId(),
        3,
        150,
        1,
        dbuser
      )
    );

    sessionContentRepository.save(
      new SessionContent(
        sessionC.getId(),
        savedExercises.get(7).getId(),
        3,
        100,
        2,
        dbuser
      )
    );

    sessionContentRepository.save(
      new SessionContent(
        sessionC.getId(),
        savedExercises.get(8).getId(),
        4,
        50,
        3,
        dbuser
      )
    );

    FitRecord recordA = fitRecordRepository.save(new FitRecord("Record A", dbuser));

    fitSetRepository.save(new FitSet(recordA, savedExercises.get(0), 1, 6, 55));
    fitSetRepository.save(new FitSet(recordA, savedExercises.get(0), 2, 8, 50));
    fitSetRepository.save(new FitSet(recordA, savedExercises.get(0), 3, 10, 45));

    fitSetRepository.save(new FitSet(recordA, savedExercises.get(1), 1, 6, 55));
    fitSetRepository.save(new FitSet(recordA, savedExercises.get(1), 2, 8, 50));
    fitSetRepository.save(new FitSet(recordA, savedExercises.get(1), 3, 10, 45));

    fitSetRepository.save(new FitSet(recordA, savedExercises.get(2), 1, 8, 10));
    fitSetRepository.save(new FitSet(recordA, savedExercises.get(2), 2, 10, 8));
    fitSetRepository.save(new FitSet(recordA, savedExercises.get(2), 3, 12, 6));
    fitSetRepository.save(new FitSet(recordA, savedExercises.get(2), 4, 14, 4));

    log.info("Database loaded.");
  }
}
