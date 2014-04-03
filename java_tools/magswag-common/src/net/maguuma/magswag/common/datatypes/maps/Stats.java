package net.maguuma.magswag.common.datatypes.maps;

import java.util.HashMap;
import net.maguuma.magswag.common.constants.Stat;

@SuppressWarnings("serial")
public class Stats extends HashMap<Stat, Integer> {
  @Override
  public Integer get(Object stat) {
    Integer bonus = super.get(stat);
    if (bonus == null) {
      return 0;
    }
    return bonus;
  }
}
