package net.maguuma.magswag.common.datatypes.datastore;

import java.util.Collection;

public class ListDataStore<E> extends DataStore<Integer, E> {
  public void addDatum(E datum) {
    int index = this.data.size();
    while (this.data.get(index) != null) {
      index++;
    }
    addDatum(index, datum);
  }

  public void addData(Collection<E> data) {
    for (E datum : data) {
      addDatum(datum);
    }
  }
}
