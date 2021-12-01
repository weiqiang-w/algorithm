package algorithm.stack;

import java.util.Stack;

/**
 * 实现 一个 堆栈 pop push getMin()
 *
 * Created on 2021-07-14
 */

class StackFuc1{
    private Stack<Integer> dataStack = new Stack<Integer>();
    private Stack<Integer> minStack = new Stack<Integer>();

    public void push(int data) {
        if (dataStack.isEmpty()) {
            minStack.push(data);
        } else if (data <= minStack.peek()) {
            minStack.push(data);
        } else {
            minStack.push(minStack.peek());
        }
        dataStack.push(data);
    }

    public int pop() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("the algorithm.stack is empty");
        }
        minStack.pop();
        return dataStack.pop();
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return minStack.peek();
    }
}

class StackFuc2{
    private Stack<Integer> dataStack = new Stack<Integer>();
    private Stack<Integer> minStack = new Stack<Integer>();

    public void push(int data) {
        if (minStack.isEmpty()) {
            minStack.push(data);
        } else if (data <= minStack.peek()) {
            minStack.push(data);
        }
        dataStack.push(data);
    }

    public int pop() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("the algorithm.stack is empty");
        }
        if (dataStack.peek().equals(minStack.peek())){
            minStack.pop();
        }
        return dataStack.pop();
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return minStack.peek();
    }
}

public class Stack1 {
    public static void main(String[] args) {
        StackFuc2 stack1 = new StackFuc2();
        stack1.push(2);
        stack1.push(5);
        stack1.push(4);
        stack1.push(1);
        stack1.push(3);
        stack1.push(1);
        System.out.println(stack1.getMin());//1
        System.out.println(stack1.pop());//1
        System.out.println(stack1.getMin());//1
        System.out.println(stack1.pop());//3
        System.out.println(stack1.getMin());//1
        System.out.println(stack1.pop());//1
        System.out.println(stack1.getMin());//2
        System.out.println(stack1.pop());//4
        System.out.println(stack1.getMin());//2
        System.out.println(stack1.pop());//5
        System.out.println(stack1.getMin());//2
        System.out.println(stack1.pop());//2
        System.out.println(stack1.getMin());//error
    }
}
