package net.maguuma.magswag.calculator.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import net.maguuma.magswag.calculator.WeightCalculator;
import net.maguuma.magswag.calculator.character.Character;
import net.maguuma.magswag.calculator.controller.listener.CharacterChangeListener;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.common.datatypes.items.StatType;
import net.maguuma.magswag.common.logging.Logger;

public class CharacterController {
  private static Character character = new Character();
  private static List<Character> characters = new ArrayList<Character>();
  private static int HIGH_WEIGHT = 0;
  private static double WEIGHT_BUFFER = 0.95;

  private static Set<CharacterChangeListener> listeners = new HashSet<CharacterChangeListener>();

  public static void fireCharacterChanged() {
    for (CharacterChangeListener listener : listeners) {
      listener.characterChanged();
    }
  }

  public static List<Character> getCharacters() {
    return characters;
  }

  public static void calculateCharacters() {
    HIGH_WEIGHT = 0;
    characters.clear();
    Character baseChar = createCharacter(character);
    processEquipment(baseChar);
    Logger.trace("Done");
  }

  private static void processEquipment(Character character) {
    for (Slot slot : Slot.values()) {
      if (character.getEquipmentModel().getGear(slot) == null) {
        for (GearType gearType : DataHandler.GEAR_TYPES.getData()) {
          if (slot.applicable(gearType.getEquipmentType())) {
            for (StatType statType : DataHandler.STAT_TYPES.getData()) {
              Equipment equip = new Equipment(gearType, CharacterController.getCharacter().getProfessionModel().getProfession().getArmor(), statType, Rarity.ASCENDED);
              if (WeightCalculator.calculateWeight(equip) > 0) {
                character.getEquipmentModel().setGear(slot, equip);
                if (goodWeight(character)) {
                  Character nextChar = createCharacter(character);
                  processEquipment(nextChar);
                }
              }
            }
          }
        }
      }
    }
    Logger.trace("Adding character " + character);
    characters.add(character);
  }

  private static boolean goodWeight(Character character) {
    int weight = WeightCalculator.calculateWeight(character.getStats());
    if (weight >= HIGH_WEIGHT * WEIGHT_BUFFER) {
      if (weight >= HIGH_WEIGHT) {
        HIGH_WEIGHT = weight;
      }
      return true;
    }
    return false;
  }

  private static Character createCharacter(Character character) {
    Logger.debug("Creating new character cloned from " + character);
    Character newCharacter = new Character();
    newCharacter.getProfessionModel().setProfession(character.getProfessionModel().getProfession());
    newCharacter.getTraitModel().setTraits(character.getTraitModel().getTraits());
    for (Entry<Slot, Equipment> entry : character.getEquipmentModel().getGear().entrySet()) {
      newCharacter.getEquipmentModel().setGear(entry.getKey(), entry.getValue());
    }
    return newCharacter;
  }

  public static Character getCharacter() {
    return character;
  }

  public static void addCharacterChangeListener(CharacterChangeListener listener) {
    listeners.add(listener);
  }

  public static void removeCharacterChangeListener(CharacterChangeListener listener) {
    listeners.remove(listener);
  }
}
