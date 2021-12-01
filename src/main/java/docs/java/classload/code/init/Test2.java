package docs.java.classload.code.init;

public class Test2 {
    public static void main(String[] args) {
        new SubInitClass2();
    }
}

class InitClass2{
    static{
        System.out.println("运行父类静态代码");
    }
    public static Field1 f1 = new Field1();
    public static Field2 f2;
    public  Field3 f3 = new Field3();
}

class SubInitClass2 extends InitClass2{
    static{
        System.out.println("运行子类静态代码");
    }
    public static Field2 f2 = new Field2();
    public  Field4 f4 = new Field4();

}