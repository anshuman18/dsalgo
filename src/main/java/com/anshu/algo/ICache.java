/**
 * 
 */
package com.anshu.algo;

/**
 * @author anshuman
 *
 */
public interface ICache<K, V> {

	V get(K key);

	void put(K key, V value);

}
