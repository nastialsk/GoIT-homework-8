interface MyQueueInterface<T> {

    void add(T value);

    T remove(int index);

    void clear();

    int size();

    T peek();

    T poll();
}
