package com.platine.myFitBuddy.config;

import com.github.javafaker.Faker;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.repository.ExerciseRepository;
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
  private ExerciseRepository exerciseRepository;

  @Autowired
  private SessionRepository sampleRepository;

  private final Faker faker = new Faker();

  @Override
  public void run(String... args) throws Exception {
    log.info("Loading database...");

    // Users
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    DBUser dbUser = new DBUser(
      "dbuser",
      "dbuser@univ-lille.fr",
      passwordEncoder.encode("dbuser"),
      "USER"
    );
    dbUserRepository.save(dbUser);
    dbUserRepository.save(
      new DBUser(
        "admin",
        "myfitadmin@univ-lille.fr",
        passwordEncoder.encode("admin"),
        "ADMIN"
      )
    );

    // Exercises
    String[] exercises = {
      "BENCH_PRESS",
      "INCLINE_BENCH_PRESS",
      "DECLINE_BENCH_PRESS",
      "DUMBBELL_FLYES",
      "MACHINE_FLYES",
      "CHEST_DIPS",
      "PEC_DECK_FLY",
      "PUSH_UPS",
      "PULL_UPS",
      "BARBELL_ROW",
      "DUMBBELL_ROW",
      "MACHINE_ROW",
      "SEATED_CABLE_ROW",
      "LAT_PULLDOWN",
      "DEADLIFT",
      "PULL_OVER",
      "SUPERMAN",
      "SQUAT",
      "LEG_PRESS",
      "LUNGES",
      "LEG_EXTENSION",
      "HACK_SQUAT",
      "LEG_CURL",
      "STIFF_LEGGED_DEADLIFT",
      "GOOD_MORNING",
      "STANDING_CALF_RAISE",
      "SEATED_CALF_RAISE",
      "MILITARY_PRESS",
      "ARNOLD_PRESS",
      "LATERAL_RAISE",
      "FRONT_RAISE",
      "REAR_DELT_FLY",
      "FACE_PULL",
      "BARBELL_CURL",
      "DUMBBELL_CURL",
      "INCLINE_DUMBBELL_CURL",
      "CONCENTRATION_CURL",
      "HAMMER_CURL",
      "CABLE_CURL",
      "CLOSE_GRIP_BENCH_PRESS",
      "BARBELL_TRICEPS_EXTENSION",
      "DUMBBELL_TRICEPS_EXTENSION",
      "TRICEPS_DIPS",
      "CABLE_TRICEPS_EXTENSION",
      "SKULL_CRUSHER",
      "CRUNCHES",
      "LEG_RAISES",
      "PLANK",
      "RUSSIAN_TWIST",
      "MOUNTAIN_CLIMBERS",
      "V_UPS",
      "SIDE_PLANK",
      "AB_WHEEL_ROLLOUT"
    };

    for (String exercise : exercises) {
      exerciseRepository.save(new Exercise(exercise));
    }

    // Sessions
    for (int i = 0; i < 10; i++) {
      sampleRepository.save(new Session(faker.name().title(), dbUser));
    }

    log.info("Database loaded.");
  }
}
