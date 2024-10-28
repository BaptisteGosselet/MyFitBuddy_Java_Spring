package com.platine.myFitBuddy.features.exercices.service;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.repository.ExerciseRepository;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Service
public class ExerciseServiceImpl implements ExerciseService {
  private static final String IMAGE_DIRECTORY = "classpath:/images/exercises/"; // Vérifiez la casse ici
  private static final String IMAGE_EXTENSION = ".jpg";

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public List<Exercise> getAllExercises() {
    return exerciseRepository.findAll();
  }

  @Override
  public Exercise getExerciseById(final long id) {
    return exerciseRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + id));
  }

  @Override
  public StreamingResponseBody getExerciseImage(final long id) {
    Exercise exercise = getExerciseById(id);
    String exerciseKey = exercise.getKey();
    String filename = IMAGE_DIRECTORY + exerciseKey + IMAGE_EXTENSION;

    Resource imageResource = resourceLoader.getResource(filename);

    if (!imageResource.exists()) {
      throw new RuntimeException("Image not found for exercise with id: " + id);
    }

    return outputStream -> {
      try (InputStream imageStream = imageResource.getInputStream()) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = imageStream.read(buffer)) != -1) {
          outputStream.write(buffer, 0, bytesRead);
        }
      } catch (Exception e) {
        throw new RuntimeException(
          "Failed to stream image for exercise with id: " + id,
          e
        );
      }
    };
  }
}
