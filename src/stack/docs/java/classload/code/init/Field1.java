package stack.docs.java.classload.code.init;

public class Field1 {
    {
        System.out.println("Field1静态代码块");
    }
    public Field1(){
        System.out.println("Field1构造方法");
    }
}
