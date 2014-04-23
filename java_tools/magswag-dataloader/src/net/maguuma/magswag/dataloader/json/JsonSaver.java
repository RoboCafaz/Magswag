package net.maguuma.magswag.dataloader.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class JsonSaver {
  public static String saveData(String fileName, String contents) throws IOException {
    File file = new File(fileName);
    file.createNewFile();
    FileOutputStream fOut = new FileOutputStream(file);
    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
    myOutWriter.append(contents);
    myOutWriter.close();
    fOut.close();
    return file.getAbsolutePath();
  }
}
