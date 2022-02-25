package docs.java;

import java.util.ArrayList;

/**
 */

public class OOM {

    static class OOMObj{
        private Integer xxx;
        private String abc;
        private String cdf;
    }

    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        for (;;){
            objects.add(new OOMObj());
        }
    }
}
