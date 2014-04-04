package net.maguuma.magswag.dataloader.json;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.constants.Weight;
import net.maguuma.magswag.common.datatypes.items.Armor;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.items.Item;
import net.maguuma.magswag.dataloader.datatypes.ArmorType;
import net.maguuma.magswag.dataloader.datatypes.ArmorTypes;
import net.maguuma.magswag.dataloader.datatypes.StatType;
import net.maguuma.magswag.dataloader.datatypes.StatTypes;

public class ItemFactory {
  public static List<Item> createItems() {
    String url;
    ArmorTypes armorTypes = null;
    StatTypes statTypes = null;
    try {
      url = "resource/ArmorTypes.json";
      armorTypes = DataLoader.load(url, ArmorTypes.class);

      url = "resource/StatTypes.json";
      statTypes = DataLoader.load(url, StatTypes.class);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return new ArrayList<Item>();
    }
    List<Item> items = createItems(armorTypes, statTypes);
    return items;
  }

  private static List<Item> createItems(ArmorTypes armorTypes, StatTypes statTypes) {
    List<Item> items = new ArrayList<Item>();
    for (ArmorType armorType : armorTypes.armorTypes) {
      items.addAll(createItems(armorType, statTypes));
    }
    return items;
  }

  private static List<Item> createItems(ArmorType armorType, StatTypes statTypes) {
    List<Item> items = new ArrayList<Item>();
    for (StatType statType : statTypes.statTypes) {
      items.addAll(createItems(armorType, statType));
    }
    return items;
  }

  private static List<Item> createItems(ArmorType armorType, StatType statType) {
    List<Item> items = new ArrayList<Item>();
    for (Slot slot : Slot.values()) {
      items.addAll(createItems(slot, armorType, statType));
    }
    return items;
  }

  private static List<Item> createItems(Slot slot, ArmorType armorType, StatType statType) {
    List<Item> items = new ArrayList<Item>();
    for (Rarity rarity : Rarity.values()) {
      items.addAll(createItems(rarity, slot, armorType, statType));
    }
    return items;
  }

  private static List<Item> createItems(Rarity rarity, Slot slot, ArmorType armorType, StatType statType) {
    List<Item> items = new ArrayList<Item>();

    if (armorType.armor != null) {
      for (Weight weight : Weight.values()) {
        items.addAll(createItems(weight, rarity, slot, armorType, statType));
      }
    } else {
      items.addAll(createItems(null, rarity, slot, armorType, statType));
    }

    return items;
  }

  private static List<Item> createItems(Weight weight, Rarity rarity, Slot slot, ArmorType armorType, StatType statType) {
    List<Item> items = new ArrayList<Item>();
    String name = rarity + " " + statType.name.toUpperCase() + " " + slot;
    Item item = null;

    boolean celestial = (statType.stats.length > 3);
    int statIndex = 0;
    int rarityIndex = rarity.ordinal();

    if (weight != null) {
      item = new Armor(name, slot, rarity, weight);
    } else {
      item = new Equipment(name, slot, rarity);
    }

    if (armorType.armor != null) {
      int armorIndex = 0;
      if (weight != null) {
        armorIndex = weight.ordinal();
      }
      int value = 0;
      value = armorType.armor[rarityIndex][armorIndex];
      item.addStat(Stat.ARMOR, value);
    }

    for (Stat stat : statType.stats) {
      int value = 0;
      if (celestial) {
        value = armorType.celestial[rarityIndex];
      } else {
        value = armorType.stats[rarityIndex][statIndex];
        if (statIndex == 0) {
          statIndex = 1;
        }
      }
      item.addStat(stat, value);
    }
    items.add(item);
    return items;
  }
}
