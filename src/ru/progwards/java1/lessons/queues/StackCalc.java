package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackCalc {

    private Deque<Double> stack = new ArrayDeque<>();

    public Deque<Double> getStack() {
        return stack;
    }

    // положить value на вершину стека
    public void push(double value) {
        stack.push(value);
    }

    // взять (убрать) значение с вершины стека
    public double pop() {
        return stack.pop();
    }

    // сложить 2 верхних значения на стеке, результат положить на стек.
    public void add(){
        push(pop() + pop());
    }

    // вычесть верхнее значение на стеке, из следующего по глубине, результат положить на стек.
    public void sub() {
        double first = pop();
        push(pop() - first);
    }

    // умножить 2 верхних значения на стеке, результат положить на стек
    public void mul(){
        push(pop() * pop());
    }

    // поделить на верхнее значение на стеке, следующее по глубине, результат положить на стек.
    public void div(){
        double first = pop();
        push(pop() / first);
    }
}
