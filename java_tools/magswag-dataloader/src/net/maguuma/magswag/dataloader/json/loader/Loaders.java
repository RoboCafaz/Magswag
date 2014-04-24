package net.maguuma.magswag.dataloader.json.loader;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.ImageIcon;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.common.datatypes.items.StatType;
import net.maguuma.magswag.common.datatypes.professions.Profession;
import net.maguuma.magswag.dataloader.image.ImageLoader;
import net.maguuma.magswag.dataloader.json.JsonLoader;
import net.maguuma.magswag.dataloader.json.wrappers.GearTypes;
import net.maguuma.magswag.dataloader.json.wrappers.Professions;
import net.maguuma.magswag.dataloader.json.wrappers.StatTypes;

public class Loaders {
  public static void loadData() {
    loadProfessions();
    loadStatTypes();
    loadGearTypes();
  }

  private static void loadProfessions() {
    Professions profs = JsonLoader.load("resource/database/Professions.json", Professions.class);
    if (profs != null) {
      for (Profession prof : profs.professions) {
        BufferedImage image = ImageLoader.loadImage("resource/images/icons/" + prof.getName() + ".png");
        if (image != null) {
          prof.setIcon(new ImageIcon(image));
        }
      }
      List<Profession> sortedProfessions = Arrays.asList(profs.professions);
      Collections.sort(sortedProfessions, new Comparator<Profession>() {
        @Override
        public int compare(Profession o1, Profession o2) {
          return o1.getName().compareTo(o2.getName());
        }
      });
      DataHandler.PROFESSIONS.addData(sortedProfessions);
    }
  }

  private static void loadStatTypes() {
    StatTypes stats = JsonLoader.load("resource/database/StatTypes.json", StatTypes.class);
    if (stats != null) {
      List<StatType> statType = Arrays.asList(stats.statTypes);
      Collections.sort(statType, new Comparator<StatType>() {
        @Override
        public int compare(StatType o1, StatType o2) {
          return o1.getName().compareTo(o2.getName());
        }
      });
      DataHandler.STAT_TYPES.addData(statType);
    }
  }

  private static void loadGearTypes() {
    GearTypes gear = JsonLoader.load("resource/database/GearTypes.json", GearTypes.class);
    if (gear != null) {
      List<GearType> sortedGear = Arrays.asList(gear.gearTypes);
      Collections.sort(sortedGear, new Comparator<GearType>() {
        @Override
        public int compare(GearType o1, GearType o2) {
          return o1.getSlot().compareTo(o2.getSlot());
        }
      });
      DataHandler.GEAR_TYPES.addData(sortedGear);
    }
  }
}
