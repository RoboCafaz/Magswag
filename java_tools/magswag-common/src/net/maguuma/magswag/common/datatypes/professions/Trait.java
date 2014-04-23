package net.maguuma.magswag.common.datatypes.professions;

import net.maguuma.magswag.common.constants.Stat;

public class Trait {
  private String name;
  private Stat primary;
  private Stat secondary;

  public Trait() {
  }

  public String getName() {
    return this.name;
  }

  public Stat getPrimary() {
    return this.primary;
  }

  public Stat getSecondary() {
    return this.secondary;
  }
}
