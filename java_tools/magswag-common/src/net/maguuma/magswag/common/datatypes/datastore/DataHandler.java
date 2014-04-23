package net.maguuma.magswag.common.datatypes.datastore;

import net.maguuma.magswag.common.datatypes.items.Item;
import net.maguuma.magswag.common.datatypes.professions.Profession;

public class DataHandler {
  public static final DataStore<Profession> PROFESSIONS = new DataStore<Profession>();
  public static final DataStore<Item> ITEMS = new DataStore<Item>();
}
