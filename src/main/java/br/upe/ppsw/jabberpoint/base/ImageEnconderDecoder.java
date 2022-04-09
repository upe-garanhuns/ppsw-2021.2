package br.upe.ppsw.jabberpoint.base;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import org.apache.commons.io.FileUtils;

public class ImageEnconderDecoder {

  public static void main(String[] args) {
    ClassLoader classLoader = ImageEnconderDecoder.class.getClassLoader();
    File inputFile = new File(classLoader
      .getResource("upe_garanhuns.jpg")
      .getFile());

    byte[] fileContent = null;
    try {
      fileContent = FileUtils.readFileToByteArray(inputFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String encodedString = Base64
      .getEncoder()
      .encodeToString(fileContent);
      System.out.println(encodedString);

    // create output file
    File outputFile = new File(inputFile
      .getParentFile()
      .getAbsolutePath() + File.pathSeparator + "upe_garanhuns_copy.jpg");

    // decode the string and write to file
    byte[] decodedBytes = Base64
      .getDecoder()
      .decode(encodedString);
    try {
      FileUtils.writeByteArrayToFile(outputFile, decodedBytes);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
