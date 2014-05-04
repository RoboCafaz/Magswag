package net.maguuma.magswag.calculator.controller;

import java.util.HashSet;
import java.util.Set;

import net.maguuma.magswag.calculator.character.Character;
import net.maguuma.magswag.calculator.controller.listener.CharacterChangeListener;

public class CharacterController {
  private static Character character = new Character();

  private static Set<CharacterChangeListener> listeners = new HashSet<CharacterChangeListener>();

  public static void fireCharacterChanged() {
    for (CharacterChangeListener listener : listeners) {
      listener.characterChanged();
    }
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
