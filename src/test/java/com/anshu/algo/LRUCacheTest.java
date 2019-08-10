/**
 * 
 */
package com.anshu.algo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author anshuman
 *
 */
class LRUCacheTest {

  private static class Pair {

    private final String key;

    private final String value;

    public Pair(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String getKey() {
      return key;
    }

    public String getValue() {
      return value;
    }
  }


  private LRUCache<String, String> cache;

  private static final int SIZE = 5;

  private List<Pair> firstPairList;
  private List<Pair> secondPairList;


  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {

    cache = new LRUCache<>(SIZE);
    firstPairList = new ArrayList<>(SIZE);
    secondPairList = new ArrayList<>(SIZE);

    for (int i = 1; i <= SIZE; i++) {
      firstPairList.add(new Pair("page_" + i, "http://page" + i));
      secondPairList.add(new Pair("page_" + SIZE + i, "http://page" + SIZE + i));
    }
  }


  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    firstPairList.clear();
    secondPairList.clear();

    cache = null;
  }

  /**
   * Test method for {@link com.anshu.algo.LRUCache#get(java.lang.Object)}.
   */
  @Test
  final void testGet() {

    firstPairList.forEach(p -> {
      cache.put(p.getKey(), p.getValue());
    });

    firstPairList.forEach(p -> {
      cache.put(p.getKey(), p.getValue());
      assertEquals(p.getValue(), cache.get(p.getKey()));
    });

  }

  /**
   * Test method for {@link com.anshu.algo.LRUCache#put(java.lang.Object, java.lang.Object)}.
   */
  @Test
  final void testPut() {
    firstPairList.forEach(p -> {
      cache.put(p.getKey(), p.getValue());
    });

    secondPairList.forEach(p -> {
      cache.put(p.getKey(), p.getValue());
    });

    secondPairList.forEach(p -> {
      assertEquals(p.getValue(), cache.get(p.getKey()));
    });
  }

  /**
   * Test method for {@link com.anshu.algo.LRUCache#removeExistingEntry(java.lang.Object)}.
   */
  @Test
  final void testRemoveExistingEntry() {
    String key = "page_x";
    String newValue = "http://page_y";
    cache.put(key, "http://page_x");
    cache.put(key, newValue);
    assertEquals(newValue, cache.get(key));
  }

  /**
   * Test method for {@link com.anshu.algo.LRUCache#purge()}.
   */
  @Test
  final void testPurge() {
    firstPairList.forEach(p -> {
      cache.put(p.getKey(), p.getValue());
    });

    cache.purge();


    for (int i = 1; i < firstPairList.size(); i++) {
      assertEquals(firstPairList.get(i).getValue(), cache.get(firstPairList.get(i).getKey()));
    }
    assertNull(cache.get(firstPairList.get(0).getKey()));
  }

}
