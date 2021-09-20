package stack.docs.java.functional;

@FunctionalInterface
public interface TestInterface {
    // 抽象方法
    public void sub();

    // java.lang.Object中的public方法
    @Override
    public boolean equals(Object var1);

    // 默认方法
    public default void defaultMethod(){

    }

    // 静态方法
    public static void staticMethod(){

    }
}