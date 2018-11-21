package com.example.demo;;

import java.util.Map;
import java.util.HashMap;

class Cache {
  private Map<String, CacheEntry> cache;

  Cache() {
    this.cache = new HashMap<String, CacheEntry>();
  }

  public void put(String key, byte[] value) {
    this.cache.put(key, new CacheEntry(value));
  }

  public byte[] get(String key) {
    CacheEntry data = this.cache.get(key);
    if (data == null) {
      return null;
    }

    return data.data();
  }

  public void invalidate(String key) {
    this.cache.remove(key);
  }
}
