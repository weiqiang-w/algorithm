package docs.java.jmh;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void test() {
        int a = 0;
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 600; i++) {
            test();
        }
        TimeUnit.SECONDS.sleep(1);
    }
}
