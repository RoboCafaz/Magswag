package net.maguuma.magswag.dataloader.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import com.google.gson.Gson;

public class JsonLoader {
  public static <T> T load(String filename, final Class<T> clazz) throws FileNotFoundException {
    File file = new File(filename);
    FileInputStream inputStream = new FileInputStream(file);
    InputStreamReader streamReader = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(streamReader);
    Gson gson = new Gson();
    return gson.fromJson(reader, clazz);
  }
}
