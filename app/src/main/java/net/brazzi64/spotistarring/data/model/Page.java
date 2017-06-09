package net.brazzi64.spotistarring.data.model;

import java.util.ArrayList;
import java.util.List;


public class Page<T> {

  public final String href;

  public final List<T> items;

  public final int limit;

  public final int offset;

  public final int total;

  public Page(String href, int limit, int offset, int total) {
    this.href = href;
    this.limit = limit;
    this.offset = offset;
    this.total = total;
    this.items = new ArrayList<>();
  }
}
