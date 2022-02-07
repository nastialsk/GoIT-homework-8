interface MyHashMapInterface<K, V> {

    void put(K key, V value);

    void remove(K key);

    void clear();

    int size();

    V get(K key);
}
