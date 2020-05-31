package ru.progwards.java1.lessons.arrays;

public class Eratosthenes {
    private boolean[] sieve;

    public Eratosthenes(int N) {
        sieve = new boolean[N];
        for (int i = 0; i < N; i++) {
            sieve[i] = true;
        }
        sieve[0] = false;
        sieve[1] = false;
    }

    private void sift() {
        for (int i = 2; i < sieve.length - 1; i++) {
            for (int j = i; j * i < sieve.length; j++) {
                sieve[j * i] = false;
            }
        }
    }

    public boolean isSimple(int n) {
        return sieve[n];
    }

}
