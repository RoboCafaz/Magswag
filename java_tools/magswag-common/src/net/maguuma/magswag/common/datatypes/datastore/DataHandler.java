package net.maguuma.magswag.common.datatypes.datastore;

import net.maguuma.magswag.common.constants.EquipmentType;
import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.common.datatypes.items.StatType;
import net.maguuma.magswag.common.datatypes.professions.Profession;

public class DataHandler {
  public static final ListDataStore<Profession> PROFESSIONS = new ListDataStore<Profession>();
  public static final DataStore<EquipmentType, GearType> GEAR_TYPES = new DataStore<EquipmentType, GearType>();
  public static final ListDataStore<StatType> STAT_TYPES = new ListDataStore<StatType>();
}
