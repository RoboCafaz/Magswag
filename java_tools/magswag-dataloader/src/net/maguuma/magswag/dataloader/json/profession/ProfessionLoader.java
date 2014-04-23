package net.maguuma.magswag.dataloader.json.profession;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.swing.ImageIcon;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.professions.Profession;
import net.maguuma.magswag.dataloader.image.ImageLoader;
import net.maguuma.magswag.dataloader.json.JsonLoader;
import net.maguuma.magswag.dataloader.json.datatypes.Professions;

public class ProfessionLoader {
  public static void loadProfessions() {
    Professions profs = null;
    try {
      profs = JsonLoader.load("resource/database/Professions.json", Professions.class);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (profs != null) {
      for (Profession prof : profs.professions) {
        BufferedImage image = ImageLoader.loadImage("resource/images/icons/" + prof.getName() + ".png");
        if (image != null) {
          prof.setIcon(new ImageIcon(image));
        }
      }
      DataHandler.PROFESSIONS.addData(Arrays.asList(profs.professions));
    }
  }
}
