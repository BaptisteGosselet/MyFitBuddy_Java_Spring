package com.platine.myFitBuddy.config;

import com.github.javafaker.Faker;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import com.platine.myFitBuddy.features.exercices.repository.ExerciseRepository;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
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
      sampleRepository.save(new FitSession(faker.name().title(), dbUser));
    }

    log.info("Database loaded.");
  }

  private void loadExercises() throws Exception {
    exerciseRepository.save(
      new Exercise(
        "BENCH_PRESS",
        MuscleGroup.PECTORALS,
        "Bench Press",
        "Developpe couche"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "INCLINE_BENCH_PRESS",
        MuscleGroup.PECTORALS,
        "Incline Bench Press",
        "Developpe incline"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "DECLINE_BENCH_PRESS",
        MuscleGroup.PECTORALS,
        "Decline Bench Press",
        "Developpe decline"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "DUMBBELL_FLYES",
        MuscleGroup.PECTORALS,
        "Dumbbell Flyes",
        "Ecarte avec halteres"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "MACHINE_FLYES",
        MuscleGroup.PECTORALS,
        "Machine Flyes",
        "Ecarte a la machine"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "CHEST_DIPS",
        MuscleGroup.PECTORALS,
        "Chest Dips",
        "Dips pour les pectoraux"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "CABLE_FLYES",
        MuscleGroup.PECTORALS,
        "Cable Flyes",
        "Ecarte aux cables"
      )
    );
    exerciseRepository.save(
      new Exercise("PUSH_UPS", MuscleGroup.PECTORALS, "Push Ups", "Pompes")
    );

    // Exercices pour les Dorsaux
    exerciseRepository.save(
      new Exercise("PULL_UPS", MuscleGroup.BACK, "Pull Ups", "Tractions")
    );
    exerciseRepository.save(
      new Exercise("BARBELL_ROW", MuscleGroup.BACK, "Barbell Row", "Rowing barre")
    );
    exerciseRepository.save(
      new Exercise("DUMBBELL_ROW", MuscleGroup.BACK, "Dumbbell Row", "Rowing haltere")
    );
    exerciseRepository.save(
      new Exercise("MACHINE_ROW", MuscleGroup.BACK, "Machine Row", "Rowing machine")
    );
    exerciseRepository.save(
      new Exercise(
        "SEATED_CABLE_ROW",
        MuscleGroup.BACK,
        "Seated Cable Row",
        "Rowing assis a la poulie"
      )
    );
    exerciseRepository.save(
      new Exercise("LAT_PULLDOWN", MuscleGroup.BACK, "Lat Pulldown", "Tirage lateral")
    );
    exerciseRepository.save(
      new Exercise("DEADLIFT", MuscleGroup.BACK, "Deadlift", "Souleve de terre")
    );
    exerciseRepository.save(
      new Exercise("PULL_OVER", MuscleGroup.BACK, "Pull Over", "Pull-over")
    );
    exerciseRepository.save(
      new Exercise("SUPERMAN", MuscleGroup.BACK, "Superman", "Superman")
    );

    // Exercices pour les Jambes
    // Quadriceps
    exerciseRepository.save(new Exercise("SQUAT", MuscleGroup.LEGS, "Squat", "Squat"));
    exerciseRepository.save(
      new Exercise("LEG_PRESS", MuscleGroup.LEGS, "Leg Press", "Presse a jambes")
    );
    exerciseRepository.save(new Exercise("LUNGES", MuscleGroup.LEGS, "Lunges", "Fentes"));
    exerciseRepository.save(
      new Exercise(
        "LEG_EXTENSION",
        MuscleGroup.LEGS,
        "Leg Extension",
        "Extension de jambes"
      )
    );
    exerciseRepository.save(
      new Exercise("HACK_SQUAT", MuscleGroup.LEGS, "Hack Squat", "Hack squat")
    );

    // Ischio-jambiers
    exerciseRepository.save(
      new Exercise("LEG_CURL", MuscleGroup.LEGS, "Leg Curl", "Leg curl")
    );
    exerciseRepository.save(
      new Exercise(
        "STIFF_LEGGED_DEADLIFT",
        MuscleGroup.LEGS,
        "Stiff-Legged Deadlift",
        "Souleve de terre jambes tendues"
      )
    );
    exerciseRepository.save(
      new Exercise("GOOD_MORNING", MuscleGroup.LEGS, "Good Morning", "Good morning")
    );

    // Mollets
    exerciseRepository.save(
      new Exercise(
        "STANDING_CALF_RAISE",
        MuscleGroup.LEGS,
        "Standing Calf Raise",
        "Elevation des mollets debout"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "SEATED_CALF_RAISE",
        MuscleGroup.LEGS,
        "Seated Calf Raise",
        "Elevation des mollets assis"
      )
    );

    // Exercices pour les Epaules
    exerciseRepository.save(
      new Exercise(
        "MILITARY_PRESS",
        MuscleGroup.SHOULDERS,
        "Military Press",
        "Developpe militaire"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "ARNOLD_PRESS",
        MuscleGroup.SHOULDERS,
        "Arnold Press",
        "Developpe Arnold"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "LATERAL_RAISE",
        MuscleGroup.SHOULDERS,
        "Lateral Raise",
        "Elevation laterale"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "FRONT_RAISE",
        MuscleGroup.SHOULDERS,
        "Front Raise",
        "Elevation frontale"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "REAR_DELT_FLY",
        MuscleGroup.SHOULDERS,
        "Rear Delt Fly",
        "Ecarte arriere"
      )
    );
    exerciseRepository.save(
      new Exercise("FACE_PULL", MuscleGroup.SHOULDERS, "Face Pull", "Face pull")
    );

    // Exercices pour les Bras
    // Biceps
    exerciseRepository.save(
      new Exercise("BARBELL_CURL", MuscleGroup.ARMS, "Barbell Curl", "Curl barre")
    );
    exerciseRepository.save(
      new Exercise("DUMBBELL_CURL", MuscleGroup.ARMS, "Dumbbell Curl", "Curl haltere")
    );
    exerciseRepository.save(
      new Exercise(
        "INCLINE_DUMBBELL_CURL",
        MuscleGroup.ARMS,
        "Incline Dumbbell Curl",
        "Curl incline haltere"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "CONCENTRATION_CURL",
        MuscleGroup.ARMS,
        "Concentration Curl",
        "Curl concentration"
      )
    );
    exerciseRepository.save(
      new Exercise("HAMMER_CURL", MuscleGroup.ARMS, "Hammer Curl", "Curl marteau")
    );
    exerciseRepository.save(
      new Exercise("CABLE_CURL", MuscleGroup.ARMS, "Cable Curl", "Curl a la poulie")
    );

    // Triceps
    exerciseRepository.save(
      new Exercise(
        "CLOSE_GRIP_BENCH_PRESS",
        MuscleGroup.ARMS,
        "Close Grip Bench Press",
        "Developpe couche prise serree"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "BARBELL_TRICEPS_EXTENSION",
        MuscleGroup.ARMS,
        "Barbell Triceps Extension",
        "Extension triceps a la barre"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "DUMBBELL_TRICEPS_EXTENSION",
        MuscleGroup.ARMS,
        "Dumbbell Triceps Extension",
        "Extension triceps haltere"
      )
    );
    exerciseRepository.save(
      new Exercise(
        "TRICEPS_PUSHDOWN",
        MuscleGroup.ARMS,
        "Triceps Pushdown",
        "Pushdown triceps"
      )
    );
    exerciseRepository.save(
      new Exercise("TRICEPS_DIP", MuscleGroup.ARMS, "Triceps Dip", "Dips triceps")
    );
    exerciseRepository.save(
      new Exercise(
        "OVERHEAD_TRICEPS_EXTENSION",
        MuscleGroup.ARMS,
        "Overhead Triceps Extension",
        "Overhead Triceps Extension"
      )
    );
  }
}
