package com.platine.myFitBuddy.config;

import com.github.javafaker.Faker;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
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
    loadExercises();

    // Sessions
    for (int i = 0; i < 10; i++) {
      sampleRepository.save(new Session(faker.name().title(), dbUser));
    }

    log.info("Database loaded.");
  }

  private void loadExercises() throws Exception {
    exerciseRepository.save(new Exercise("BENCH_PRESS", MuscleGroup.PECTORALS));
    exerciseRepository.save(new Exercise("INCLINE_BENCH_PRESS", MuscleGroup.PECTORALS));
    exerciseRepository.save(new Exercise("DECLINE_BENCH_PRESS", MuscleGroup.PECTORALS));
    exerciseRepository.save(new Exercise("DUMBBELL_FLYES", MuscleGroup.PECTORALS));
    exerciseRepository.save(new Exercise("MACHINE_FLYES", MuscleGroup.PECTORALS));
    exerciseRepository.save(new Exercise("CHEST_DIPS", MuscleGroup.PECTORALS));
    exerciseRepository.save(new Exercise("CABLE_FLYES", MuscleGroup.PECTORALS));
    exerciseRepository.save(new Exercise("PUSH_UPS", MuscleGroup.PECTORALS));

    // Exercices pour les Dorsaux
    exerciseRepository.save(new Exercise("PULL_UPS", MuscleGroup.BACK));
    exerciseRepository.save(new Exercise("BARBELL_ROW", MuscleGroup.BACK));
    exerciseRepository.save(new Exercise("DUMBBELL_ROW", MuscleGroup.BACK));
    exerciseRepository.save(new Exercise("MACHINE_ROW", MuscleGroup.BACK));
    exerciseRepository.save(new Exercise("SEATED_CABLE_ROW", MuscleGroup.BACK));
    exerciseRepository.save(new Exercise("LAT_PULLDOWN", MuscleGroup.BACK));
    exerciseRepository.save(new Exercise("DEADLIFT", MuscleGroup.BACK));
    exerciseRepository.save(new Exercise("PULL_OVER", MuscleGroup.BACK));
    exerciseRepository.save(new Exercise("SUPERMAN", MuscleGroup.BACK));

    // Exercices pour les Jambes
    // Quadriceps
    exerciseRepository.save(new Exercise("SQUAT", MuscleGroup.LEGS));
    exerciseRepository.save(new Exercise("LEG_PRESS", MuscleGroup.LEGS));
    exerciseRepository.save(new Exercise("LUNGES", MuscleGroup.LEGS));
    exerciseRepository.save(new Exercise("LEG_EXTENSION", MuscleGroup.LEGS));
    exerciseRepository.save(new Exercise("HACK_SQUAT", MuscleGroup.LEGS));

    // Ischio-jambiers
    exerciseRepository.save(new Exercise("LEG_CURL", MuscleGroup.LEGS));
    exerciseRepository.save(new Exercise("STIFF_LEGGED_DEADLIFT", MuscleGroup.LEGS));
    exerciseRepository.save(new Exercise("GOOD_MORNING", MuscleGroup.LEGS));

    // Mollets
    exerciseRepository.save(new Exercise("STANDING_CALF_RAISE", MuscleGroup.LEGS));
    exerciseRepository.save(new Exercise("SEATED_CALF_RAISE", MuscleGroup.LEGS));

    // Exercices pour les Épaules
    exerciseRepository.save(new Exercise("MILITARY_PRESS", MuscleGroup.SHOULDERS));
    exerciseRepository.save(new Exercise("ARNOLD_PRESS", MuscleGroup.SHOULDERS));
    exerciseRepository.save(new Exercise("LATERAL_RAISE", MuscleGroup.SHOULDERS));
    exerciseRepository.save(new Exercise("FRONT_RAISE", MuscleGroup.SHOULDERS));
    exerciseRepository.save(new Exercise("REAR_DELT_FLY", MuscleGroup.SHOULDERS));
    exerciseRepository.save(new Exercise("FACE_PULL", MuscleGroup.SHOULDERS));

    // Exercices pour les Bras
    // Biceps
    exerciseRepository.save(new Exercise("BARBELL_CURL", MuscleGroup.ARMS));
    exerciseRepository.save(new Exercise("DUMBBELL_CURL", MuscleGroup.ARMS));
    exerciseRepository.save(new Exercise("INCLINE_DUMBBELL_CURL", MuscleGroup.ARMS));
    exerciseRepository.save(new Exercise("CONCENTRATION_CURL", MuscleGroup.ARMS));
    exerciseRepository.save(new Exercise("HAMMER_CURL", MuscleGroup.ARMS));
    exerciseRepository.save(new Exercise("CABLE_CURL", MuscleGroup.ARMS));

    // Triceps
    exerciseRepository.save(new Exercise("CLOSE_GRIP_BENCH_PRESS", MuscleGroup.ARMS));
    exerciseRepository.save(new Exercise("BARBELL_TRICEPS_EXTENSION", MuscleGroup.ARMS));
    exerciseRepository.save(new Exercise("DUMBBELL_TRICEPS_EXTENSION", MuscleGroup.ARMS));
    exerciseRepository.save(new Exercise("CABLE_TRICEPS_EXTENSION", MuscleGroup.ARMS));

    // Exercices pour les Abdominaux
    exerciseRepository.save(new Exercise("CRUNCHES", MuscleGroup.ABS));
    exerciseRepository.save(new Exercise("LEG_RAISES", MuscleGroup.ABS));
    exerciseRepository.save(new Exercise("RUSSIAN_TWIST", MuscleGroup.ABS));
    exerciseRepository.save(new Exercise("MOUNTAIN_CLIMBERS", MuscleGroup.ABS));
    exerciseRepository.save(new Exercise("AB_WHEEL_ROLLOUT", MuscleGroup.ABS));
  }
}
