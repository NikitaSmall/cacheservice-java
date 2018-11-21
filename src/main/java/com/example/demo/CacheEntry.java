package com.example.demo;;

class CacheEntry {
	private byte[] data;

	CacheEntry(byte[] data) {
		this.data = data;
	}

  public byte[] data() {
    return this.data;
  }
}
