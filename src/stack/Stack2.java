package stack;

import java.util.Stack;

/**
 * 队列 push,poll,peek
 * Created on 2021-07-14
 */
public class Stack2 {
    private Stack<Object> s1 = new Stack<>();
    private Stack<Object> s2 = new Stack<>();

    private void revertStack(){
        if (!s2.isEmpty()){
         return;
        }
        while(!s1.isEmpty()){
            s2.push(s1.pop());
        }
    }

    public void push(Object obj){
        s1.push(obj);
        revertStack();
    }

    public Object poll(){
        Object obj = s2.pop();
        revertStack();
        return obj;
    }

    public void peek(){
        s1.peek();
        revertStack();
    }

    public static void main(String[] args) {
        Stack2 stack2 = new Stack2();
        stack2.push(1);
        stack2.push(2);
        stack2.push(4);
        stack2.push(3);

        stack2.peek();//3
        System.out.println(stack2.poll());//1
        System.out.println(stack2.poll());//2
        System.out.println(stack2.poll());//4
        stack2.push(5);
        stack2.peek();//5
        System.out.println(stack2.poll());//3
        System.out.println(stack2.poll());//5
    }
}
