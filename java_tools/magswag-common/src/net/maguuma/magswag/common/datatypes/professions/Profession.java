package net.maguuma.magswag.common.datatypes.professions;

import javax.swing.Icon;
import net.maguuma.magswag.common.constants.Health;
import net.maguuma.magswag.common.constants.Weight;

public class Profession {
  private String name;
  private Weight armor;
  private Health hp;
  private Icon icon;

  public Profession() {
  }

  public String getName() {
    return this.name;
  }

  public Weight getArmor() {
    return this.armor;
  }

  public int getHealth() {
    return this.hp.getHealth();
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
  }

  public Icon getIcon() {
    return this.icon;
  }
}
