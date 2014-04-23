package net.maguuma.magswag.common.datatypes.datastore;

import java.util.Collection;
import java.util.HashSet;

public class DataStore<E> {
  private final Collection<E> data = new HashSet<E>();

  public void addDatum(E datum) {
    this.data.add(datum);
  }

  public void addData(Collection<E> data) {
    for (E datum : data) {
      addDatum(datum);
    }
  }

  public Collection<E> getData() {
    return this.data;
  }
}
