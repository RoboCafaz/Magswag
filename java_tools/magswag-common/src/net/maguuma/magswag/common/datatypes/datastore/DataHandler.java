package net.maguuma.magswag.common.datatypes.datastore;

import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.common.datatypes.items.StatType;
import net.maguuma.magswag.common.datatypes.professions.Profession;

public class DataHandler {
  public static final DataStore<Profession> PROFESSIONS = new DataStore<Profession>();
  public static final DataStore<GearType> GEAR_TYPES = new DataStore<GearType>();
  public static final DataStore<StatType> STAT_TYPES = new DataStore<StatType>();
}
