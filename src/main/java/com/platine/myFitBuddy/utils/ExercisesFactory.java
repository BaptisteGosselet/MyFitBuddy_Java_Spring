package com.platine.myFitBuddy.utils;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;

public abstract class ExercisesFactory {

  public static Exercise[] loadExercises() {
    return new Exercise[] {
      new Exercise(
        "BENCH_PRESS",
        MuscleGroup.PECTORALS,
        "Bench Press",
        "Developpe couche"
      ),
      new Exercise(
        "INCLINE_BENCH_PRESS",
        MuscleGroup.PECTORALS,
        "Incline Bench Press",
        "Developpe incline"
      ),
      new Exercise(
        "DECLINE_BENCH_PRESS",
        MuscleGroup.PECTORALS,
        "Decline Bench Press",
        "Developpe decline"
      ),
      new Exercise(
        "DUMBBELL_FLYES",
        MuscleGroup.PECTORALS,
        "Dumbbell Flyes",
        "Ecarte avec halteres"
      ),
      new Exercise(
        "MACHINE_FLYES",
        MuscleGroup.PECTORALS,
        "Machine Flyes",
        "Ecarte a la machine"
      ),
      new Exercise(
        "CHEST_DIPS",
        MuscleGroup.PECTORALS,
        "Chest Dips",
        "Dips pour les pectoraux"
      ),
      new Exercise(
        "CABLE_FLYES",
        MuscleGroup.PECTORALS,
        "Cable Flyes",
        "Ecarte aux cables"
      ),
      new Exercise("PUSH_UPS", MuscleGroup.PECTORALS, "Push Ups", "Pompes"),
      new Exercise("PULL_UPS", MuscleGroup.BACK, "Pull Ups", "Tractions"),
      new Exercise("BARBELL_ROW", MuscleGroup.BACK, "Barbell Row", "Rowing barre"),
      new Exercise("DUMBBELL_ROW", MuscleGroup.BACK, "Dumbbell Row", "Rowing haltere"),
      new Exercise("MACHINE_ROW", MuscleGroup.BACK, "Machine Row", "Rowing machine"),
      new Exercise(
        "SEATED_CABLE_ROW",
        MuscleGroup.BACK,
        "Seated Cable Row",
        "Rowing assis a la poulie"
      ),
      new Exercise("LAT_PULLDOWN", MuscleGroup.BACK, "Lat Pulldown", "Tirage lateral"),
      new Exercise("DEADLIFT", MuscleGroup.BACK, "Deadlift", "Souleve de terre"),
      new Exercise("PULL_OVER", MuscleGroup.BACK, "Pull Over", "Pull-over"),
      new Exercise("SUPERMAN", MuscleGroup.BACK, "Superman", "Superman"),
      new Exercise("SQUAT", MuscleGroup.LEGS, "Squat", "Squat"),
      new Exercise("LEG_PRESS", MuscleGroup.LEGS, "Leg Press", "Presse a jambes"),
      new Exercise("LUNGES", MuscleGroup.LEGS, "Lunges", "Fentes"),
      new Exercise(
        "LEG_EXTENSION",
        MuscleGroup.LEGS,
        "Leg Extension",
        "Extension de jambes"
      ),
      new Exercise("HACK_SQUAT", MuscleGroup.LEGS, "Hack Squat", "Hack squat"),
      new Exercise("LEG_CURL", MuscleGroup.LEGS, "Leg Curl", "Leg curl"),
      new Exercise(
        "STIFF_LEGGED_DEADLIFT",
        MuscleGroup.LEGS,
        "Stiff-Legged Deadlift",
        "Souleve de terre jambes tendues"
      ),
      new Exercise("GOOD_MORNING", MuscleGroup.LEGS, "Good Morning", "Good morning"),
      new Exercise(
        "STANDING_CALF_RAISE",
        MuscleGroup.LEGS,
        "Standing Calf Raise",
        "Elevation des mollets debout"
      ),
      new Exercise(
        "SEATED_CALF_RAISE",
        MuscleGroup.LEGS,
        "Seated Calf Raise",
        "Elevation des mollets assis"
      ),
      new Exercise(
        "MILITARY_PRESS",
        MuscleGroup.SHOULDERS,
        "Military Press",
        "Developpe militaire"
      ),
      new Exercise(
        "ARNOLD_PRESS",
        MuscleGroup.SHOULDERS,
        "Arnold Press",
        "Developpe Arnold"
      ),
      new Exercise(
        "LATERAL_RAISE",
        MuscleGroup.SHOULDERS,
        "Lateral Raise",
        "Elevation laterale"
      ),
      new Exercise(
        "FRONT_RAISE",
        MuscleGroup.SHOULDERS,
        "Front Raise",
        "Elevation frontale"
      ),
      new Exercise(
        "REAR_DELT_FLY",
        MuscleGroup.SHOULDERS,
        "Rear Delt Fly",
        "Ecarte arriere"
      ),
      new Exercise("FACE_PULL", MuscleGroup.SHOULDERS, "Face Pull", "Face pull"),
      new Exercise("BARBELL_CURL", MuscleGroup.ARMS, "Barbell Curl", "Curl barre"),
      new Exercise("DUMBBELL_CURL", MuscleGroup.ARMS, "Dumbbell Curl", "Curl haltere"),
      new Exercise(
        "INCLINE_DUMBBELL_CURL",
        MuscleGroup.ARMS,
        "Incline Dumbbell Curl",
        "Curl incline haltere"
      ),
      new Exercise(
        "CONCENTRATION_CURL",
        MuscleGroup.ARMS,
        "Concentration Curl",
        "Curl concentration"
      ),
      new Exercise("HAMMER_CURL", MuscleGroup.ARMS, "Hammer Curl", "Curl marteau"),
      new Exercise("CABLE_CURL", MuscleGroup.ARMS, "Cable Curl", "Curl a la poulie"),
      new Exercise(
        "CLOSE_GRIP_BENCH_PRESS",
        MuscleGroup.ARMS,
        "Close Grip Bench Press",
        "Developpe couche prise serree"
      ),
      new Exercise(
        "BARBELL_TRICEPS_EXTENSION",
        MuscleGroup.ARMS,
        "Barbell Triceps Extension",
        "Extension triceps a la barre"
      ),
      new Exercise(
        "DUMBBELL_TRICEPS_EXTENSION",
        MuscleGroup.ARMS,
        "Dumbbell Triceps Extension",
        "Extension triceps haltere"
      ),
      new Exercise(
        "TRICEPS_PUSHDOWN",
        MuscleGroup.ARMS,
        "Triceps Pushdown",
        "Pushdown triceps"
      ),
      new Exercise("TRICEPS_DIP", MuscleGroup.ARMS, "Triceps Dip", "Dips triceps"),
      new Exercise(
        "OVERHEAD_TRICEPS_EXTENSION",
        MuscleGroup.ARMS,
        "Overhead Triceps Extension",
        "Overhead Triceps Extension"
      )
    };
  }
}
