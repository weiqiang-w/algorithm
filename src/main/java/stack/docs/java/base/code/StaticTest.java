package stack.docs.java.base.code;

/**
 * a.3
 * b.12
 * a.=100
 * b.=12
 * x = 42
 * a = 100
 * b = 12
 */
public class StaticTest {
    static int a = 3;
    static int b;

    static void meth(int x) {
        System.out.println("x = " + x);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    static {
        System.out.println("Static block initialized.");
        b = a * 4;
        System.out.println("a." + a);
        System.out.println("b." + b);
    }

    static {
        a = 100;
        System.out.println("a.=" + a);
        System.out.println("b.=" + b);
    }

    public static void main(String args[]) {
        meth(42);
    }
}
