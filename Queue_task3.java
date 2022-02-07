class MyQueue<T> implements MyQueueInterface<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;
    
    public void add(T value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (first == null) {
            first = new Node<>(value, null, null);
            last = first;
        } else {
            Node<T> newNode = new Node<>(value, last, null);
            last.next = newNode;
            last = newNode;
        }

        size++;
    }
    
    public T peek() {
        if (first != null) {
            return first.value;
        }

        return null;
    }
    
    public T poll() {
        T result = peek();

        if (first != null) {
            remove(0);
        }

        return result;
    }
    
    public T remove(int index) {
        Node<T> nodeToRemove = findNodeByIndex(index);

        T nodeToRemoveValue = nodeToRemove.value;

        if (size == 1) {
            clear();

            return nodeToRemoveValue;
        }
        Node<T> prevNodeOfRemoved = nodeToRemove.prev;
        Node<T> nextNodeOfRemoved = nodeToRemove.next;

        if (prevNodeOfRemoved == null) {
            nextNodeOfRemoved.prev = null;
            first = nextNodeOfRemoved;
        } else if (nextNodeOfRemoved == null) {
            prevNodeOfRemoved.next = null;
            last = prevNodeOfRemoved;
        } else {
            prevNodeOfRemoved.next = nextNodeOfRemoved;
            nextNodeOfRemoved.prev = prevNodeOfRemoved;
        }
        size--;
        nodeToRemove.next = nodeToRemove.prev = null;

        return nodeToRemoveValue;
    }

    private Node<T> findNodeByIndex(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> result;

        if (index > size / 2) {
            result = last;

            for (int i = 0; i < size - index - 1; i++) {
                result = result.prev;
            }
        } else {
            result = first;

            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        }
        return result;
    }
    
    public void clear() {
        Node<T> node = first;

        while (node != null) {
            Node<T> next = node.next;
            node.prev = node.next = null;

            node = next;
        }

        first = last = null;
        size = 0;
    }
    
    public int size() {
        return size;
    }

    private static class Node<T> {
        private final T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
