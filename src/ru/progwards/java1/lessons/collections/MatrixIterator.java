package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {
    private T[] array;
    private int size;
    private int i = 0;

    MatrixIterator(T[][] array) {
        for (T[] i: array
             ) {
            this.size += i.length;
        }
        this.array = (T[]) new Object[size];
        int s = 0; // this.array sequence
        for (T[] i: array
        ) {
            for (T j: i
                 ) {
                this.array[s] = j;
                s++;
            }
        }

    }

    @Override
    public boolean hasNext() {
        return i < size;
    }

    @Override
    public T next() {
        return array[i++];
    }
}
