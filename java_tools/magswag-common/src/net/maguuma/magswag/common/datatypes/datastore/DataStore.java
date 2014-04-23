package net.maguuma.magswag.common.datatypes.datastore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStore<E> {
  private final List<E> data = new ArrayList<E>();

  public void addDatum(E datum) {
    this.data.add(datum);
  }

  public void addData(Collection<E> data) {
    for (E datum : data) {
      addDatum(datum);
    }
  }

  public List<E> getData() {
    return this.data;
  }
}
