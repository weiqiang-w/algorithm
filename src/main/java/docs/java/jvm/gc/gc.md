[Java Platform, Standard Edition HotSpot Virtual Machine Garbage Collection Tuning Guide][https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/]
[简书][https://www.jianshu.com/p/26b112b73c9d]

logging the GC info by command :    -XX:+PrintGCDetails -XX:+PrintGCDateStamps 
![minor gc]("./minor-gc.jpeg")
1.  2021-09-28T11:20:00.870-0800: [GC (Allocation Failure) [PSYoungGen: 65536K->3896K(76288K)] 65536K->3896K(251392K), 0.0033694 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
2.  2021-09-28T11:20:04.564-0800: [GC (Allocation Failure) [PSYoungGen: 70977K->6644K(67072K)] 70985K->14022K(242176K), 0.0069018 secs] [Times: user=0.04 sys=0.01, real=0.01 secs] 
3.  2021-09-28T11:20:05.421-0800: [GC (Metadata GC Threshold) [PSYoungGen: 8331K->352K(205312K)] 24369K->17967K(380416K), 0.0021140 secs] [Times: user=0.01 sys=0.01, real=0.00 secs]
![full-gc]("./full-gc.jpeg") 
4.  2021-09-28T11:20:05.423-0800: [Full GC (Metadata GC Threshold) [PSYoungGen: 352K->0K(205312K)] [ParOldGen: 17615K->10974K(88064K)] 17967K->10974K(293376K), [Metaspace: 20796K->20796K(1067008K)], 0.0484276 secs] [Times: user=0.28 sys=0.00, real=0.05 secs] 
 
GC (Allocation Failure): this is the reason of gc,it means there is not enough space to allocate;
PSYoungGen: defult Parallel Scavenge
65536K->3896K(76288K): the usage from 65536k to 3896k after GC, and total capacity is 76288K;
0.0033694 secs: GC cost 0.0033694 s; 
65536K->3896K(251392K): heap area usage from from 65536k to 3896k after GC, and total capacity is 251392K;

![gc-config]("./gc-config.png") 
![gc-config2]("./gc-conf2ig.jpeg") 
对照上面的图，GC日志中的PSYoungGen（PS是指Parallel Scavenge）为Eden+FromSpace，而整个YoungGeneration为Eden+FromSpace+ToSpace;
我们设置的新生代大小为10240K，这包括9216K大小的PSYoungGen和1024K大小的ToSpace。其中，PSYoungGen中的Eden:FromSpace为8:1;
这包括8192K的Eden和1024K的FromSpace;

#Full GC:
1.显式调用System.gc方法(建议JVM触发)。
2.老年代空间不足，引起Full GC。这种情况比较复杂，有以下几种：
    1.1 大对象直接进入老年代引起，由-XX:PretenureSizeThreshold参数定义
    1.2 Minor GC时，经历过多次Minor GC仍存在的对象进入老年代。上面提过，由-XX:MaxTenuringThreashold参数定义
    1.3 Minor GC时，动态对象年龄判定机制会将对象提前转移老年代。年龄从小到大进行累加，当加入某个年龄段后，累加和超过survivor区域 * -XX:TargetSurvivorRatio的时候，从这个年龄段往上的年龄的对象进入老年代
    1.4 Minor GC时，Eden和From Space区向To Space区复制时，大于To Space区可用内存，会直接把对象转移到老年代

#JDK8中HotSpot为什么要取消永久代
JDK8取消了永久代，新增了一个叫元空间(Metaspace)的区域，对应的还是JVM规范中的方法区(主要存放一些class和元数据的信息)。区别在于元空间使用的并不是JVM中的内存，而是使用本地内存。
而这么做的原因大致有以下几点：
1、字符串存在永久代中，容易出现性能问题和内存溢出。
2、类及方法的信息等比较难确定其大小，因此对于永久代的大小指定比较困难，太小容易出现永久代溢出，太大则容易导致老年代溢出。
3、永久代会为 GC 带来不必要的复杂度，并且回收效率偏低。
4、Oracle 可能会将HotSpot 与 JRockit 合二为一。