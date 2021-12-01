package design_pattern.state;


public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.start();
        context.getCpu();
        context.suspend();
        context.resume();
        context.getCpu();
        context.stop();
    }
}
