package stack.docs.java.jvm.code;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

/**
 * After java 7 there has a  getProcessCpuTime method, the calculation may be like code below
 */
public class CpuUsage {

    public static void main(String[] args) throws InterruptedException {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long lastCPUTimeNs = operatingSystemMXBean.getProcessCpuTime();
        long lastSampleTimeNs = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            long cpuTime = operatingSystemMXBean.getProcessCpuTime();
            long cpuCost = cpuTime - lastCPUTimeNs;
            long now = System.nanoTime();
            int cpuCoreNum = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
            double cpuUsage = cpuCost * 1.0d / ((now - lastSampleTimeNs) * cpuCoreNum) * 100;
            lastCPUTimeNs = cpuTime;
            lastSampleTimeNs = now;

            double processCpuLoad = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getProcessCpuLoad();
            System.out.println("last::"+lastCPUTimeNs + " cpuTime::" + cpuTime + " cpuCost::" + cpuCost +" swCpuUsage - processCpuLoad "+cpuUsage + " - "+ processCpuLoad * 100);
        }
    }
}
