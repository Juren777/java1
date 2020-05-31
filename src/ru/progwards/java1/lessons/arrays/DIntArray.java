package ru.progwards.java1.lessons.arrays;

public class DIntArray {

    private int[] a = new int[0];

    public void add(int num) {
        int l = a.length;
        int[] newArr = new int[l + 1];
        if (l > 0)
            System.arraycopy(a, 0, newArr, 0, l);
        newArr[l] = num;
        a = new int[l + 1];
        System.arraycopy(newArr, 0, a, 0, l + 1);
    }

    public void atInsert(int pos, int num) {
        int l = a.length;
        int[] newArr = new int[l + 1];
        for (int i = 0; i <= l; i++) {
            if (i < pos) {
                newArr[i] = a[i];
            } else if (i == pos) {
                newArr[i] = num;
            } else {
                newArr[i] = a[i - 1];
            }
        }
        a = new int[l + 1];
        System.arraycopy(newArr, 0, a, 0, l + 1);
    }

    public void atDelete(int pos) {
        int l = a.length;
        int[] newArr = new int[l - 1];
        for (int i = 0; i < l; i++) {
            if (i < pos) {
                newArr[i] = a[i];
            } else if (i == pos) {
                continue;
            } else {
                newArr[i - 1] = a[i];
            }
        }
        a = new int[l - 1];
        System.arraycopy(newArr, 0, a, 0, l - 1);
    }

    public int at(int pos) {
        return a[pos];
    }

    public int[] getA() {
        return a;
    }
}
