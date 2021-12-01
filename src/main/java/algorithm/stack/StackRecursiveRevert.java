package algorithm.stack;


import java.util.Stack;

public class StackRecursiveRevert {

    //递归的方法 在 return 之后继续执行。
    private static Integer getTheDeepOne(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            Integer theDeepOne = getTheDeepOne(stack);
            stack.push(pop);
            return theDeepOne;
        }
    }

    private static void revertStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer theDeepOne = getTheDeepOne(stack);
        revertStack(stack);
        stack.push(theDeepOne);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        revertStack(stack);
        System.out.println();
    }
}
















