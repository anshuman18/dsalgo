package com.anshu.algo;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author anshuman
 *
 */
public class LRUCache<K, V> implements ICache<K, V> {

  private static class Entity<K, V> {

    private final K key;

    private final V value;

    public Entity(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }
  }

  private final Map<K, Entity<K, V>> cache;

  private final Deque<Entity<K, V>> entityQ;

  private final int CACHE_SIZE;

  public LRUCache(int maxSize) {
    if (maxSize <= 0) {
      throw new IllegalArgumentException("Illegal maxSize : " + maxSize);
    }
    this.CACHE_SIZE = maxSize;
    entityQ = new LinkedList<>();
    cache = new HashMap<>(maxSize);

  }

  @Override
  public V get(K key) {
    if (cache.containsKey(key)) {
      Entity<K, V> entityValue = cache.get(key);
      entityQ.remove(entityValue);
      entityQ.addFirst(entityValue);
      return entityValue.getValue();
    }
    return null;
  }

  @Override
  public void put(K key, V value) {
    if (cache.containsKey(key)) {
      removeExistingEntry(key);
    } else {
      if (cache.size() == CACHE_SIZE) {
        purge();
      }
    }

    Entity<K, V> entity = new Entity<>(key, value);
    entityQ.addFirst(entity);
    cache.put(key, entity);
  }

  void removeExistingEntry(K key) {
    Entity<K, V> oldEntity = cache.remove(key);
    entityQ.remove(oldEntity);
  }

  void purge() {
    Entity<K, V> removed = entityQ.removeLast();
    cache.remove(removed.getKey());
  }

}
