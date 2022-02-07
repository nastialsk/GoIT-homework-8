interface MyStackInterface<T> {
    void push(T value);

    T remove(int index);

    void clear();

    int size();

    T peek();

    T pop();
}
