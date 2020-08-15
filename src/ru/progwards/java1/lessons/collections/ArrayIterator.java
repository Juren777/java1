package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;

    private int size;
    private int index = 0;

    ArrayIterator(T[] array) {
        this.array = array;
        this.size = array.length;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return index < size;
    }

    @Override
    public T next() {
        // TODO Auto-generated method stub
        index++;
        return array[index];
    }
}
