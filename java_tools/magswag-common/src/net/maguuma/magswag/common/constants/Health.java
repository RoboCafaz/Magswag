package net.maguuma.magswag.common.constants;

public enum Health {
  HIGH(9212), MEDIUM(5922), LOW(1645);

  private final int health;

  private Health(int health) {
    this.health = health;
  }

  public int getHealth() {
    return this.health;
  }
}
