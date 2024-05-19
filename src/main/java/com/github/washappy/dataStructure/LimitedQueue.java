package com.github.washappy.dataStructure;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class LimitedQueue <T>{
    private Queue<T> queue = new LinkedList<>();
    private int size;

    public LimitedQueue() {
        this.size = 1;
    }

    public LimitedQueue(int n){
        this.size = n;
    }

    public boolean add(T t) {
        if (queue.size()<size) {
            queue.add(t);
            return true;
        }
        return false;
    }

    public T remove() {
        if (!queue.isEmpty()) {
            T t = queue.peek();
            queue.remove();
            return t;
        }
        return null;
    }

    public T peek() {
        if (!queue.isEmpty()){
            return queue.peek();
        }
        return null;
    }

    public int size() {
        if (!queue.isEmpty()){
            return queue.size();
        }
        return 0;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean contains(T t) {
        return queue.contains(t);
    }

    public boolean remove(Object o) {
        return queue.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        if (queue.size() + c.size() > size) {
            return false;
        }
        queue.addAll(c);
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    public void clear() {
        queue.clear();
    }
}
