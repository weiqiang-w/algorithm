package stack.docs.java.jvm;

import java.lang.management.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
public class JvmMemory {

    private static boolean isHeap(MemoryPoolMXBean memoryPoolBean) {
        return MemoryType.HEAP.equals(memoryPoolBean.getType());
    }

    static boolean isOldGenPool(String name) {
        return name.endsWith("Old Gen") || name.endsWith("Tenured Gen");
    }

    public static void main(String[] args) {
        ArrayList<Long> heapMaxList = new ArrayList<>();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        for (MemoryPoolMXBean memoryPoolBean : ManagementFactory.getPlatformMXBeans(MemoryPoolMXBean.class)) {
            String area = MemoryType.HEAP.equals(memoryPoolBean.getType()) ? "heap" : "nonheap";
            long max = memoryPoolBean.getUsage().getMax();
            System.out.println("id: " + memoryPoolBean.getName() + " ,area: " + area + " ,max: " + max);
            if ("heap".equals(area)){
                heapMaxList.add(max);
            }
        }

        long swHeapMax = memoryMXBean.getHeapMemoryUsage().getMax();
        long swNonHeapMax = memoryMXBean.getNonHeapMemoryUsage().getMax();
        System.out.println("swHeapMax: "+swHeapMax +".swNonHeapMax: "+swNonHeapMax);


        Long mHeapMax = heapMaxList.stream().reduce(Long::sum).get();
        System.out.println("mHeapMax: "+mHeapMax +".mNonHeapMax: "+ 0);


//        Optional<MemoryPoolMXBean> first = ManagementFactory
//                .getPlatformMXBeans(MemoryPoolMXBean.class)
//                .stream()
//                .filter(JvmMemory::isHeap)
//                .filter(mem -> isOldGenPool(mem.getName())).findFirst();
//
//        double maxOldGen = first.get().getUsage().getMax();
//        System.out.println("=="+maxOldGen);
    }
}
