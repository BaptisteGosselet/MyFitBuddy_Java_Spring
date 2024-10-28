package com.platine.myFitBuddy.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public abstract class MyUtils {

  /**
   * Normalize string in order to standardize database or researches
   * @param s
   * @return
   */
  public static String normalize(final String s) {
    if (s == null) {
      return null;
    }
    String lowerCase = s.toLowerCase();
    String normalized = Normalizer.normalize(lowerCase, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    String withoutDiacritics = pattern.matcher(normalized).replaceAll("");
    return withoutDiacritics.replace(" ", "_");
  }
}
