package net.maguuma.magswag.common.datatypes.datastore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataStore<I, E> {
  protected final Map<I, E> data = new HashMap<I, E>();

  public void addDatum(I index, E datum) {
    this.data.put(index, datum);
  }

  public E get(I index) {
    return this.data.get(index);
  }

  public Collection<E> getData() {
    return this.data.values();
  }
}
