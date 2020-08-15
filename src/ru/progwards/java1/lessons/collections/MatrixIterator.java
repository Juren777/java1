package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {

    private T[] array;

    private int size;
    private int index = 0;

    MatrixIterator(T[] array) {
        this.array = array;
        this.size = array.length;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
