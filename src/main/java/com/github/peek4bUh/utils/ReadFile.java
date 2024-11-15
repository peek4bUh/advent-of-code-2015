package com.github.peek4bUh.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author peek4bUh
 */
public class ReadFile {

  /**
   * Reads given resource file as a string.
   *
   * @param fileName path to the resource file
   * @return the file's contents into a String
   */
  public static String getResourceFileAsString(String fileName) {
    ClassLoader classLoader = ReadFile.class.getClassLoader();
    try ( var inputStream = classLoader.getResourceAsStream(fileName)) {
      if (inputStream == null) {
        throw new IllegalArgumentException("File not found: " + fileName);
      }
      // Use BufferedReader for reading line by line
      try ( var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
      }
    } catch (IOException e) {
      throw new RuntimeException("Error reading file", e);
    }
  }

}
