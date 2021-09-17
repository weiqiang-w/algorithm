————————————————
原文链接：https://blog.csdn.net/yunzhonghefei/article/details/89207243

1.查看CPU占用率高的进程
    1.1> top
    1.2> 用ps -eo pid,pcpu | sort -n -k 2 
    
2.确定高cpu消耗的线程
    2.1>top -H
    2.2>ps H -eo pid,tid,pcpu | sort -n -k 3
    
3.查看进程的线程
    3.1>pstree -p pid     (pstree 安装 yum -y install psmis)
    3.2>查看线程的相信信息：cat /proc/进程号/task/线程号/status
    
4.实时线程进程的资源占用信息
    4.1>top -H -p pid
    
5.查看该进程下的所有线程
    5.1>ps -efL | grep pid
    5.2>根据pid，查找占用cpu较高的线程
        ps -mp pid -o THREAD,tid,time
6.使用jstack定位线程堆栈信息,精确找到异常代码
    6.1>查看某进程下占用CPU较高的线程：ps p 替换进程号 -L -o pcpu,pid,tid,time,tname,cmd
        

%CPU   PID   TID     TIME TTY      CMD
 0.0  1538  1538 00:00:00 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8
97.1  1538  1539 04:39:56 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8
 0.1  1538  1540 00:00:25 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8
 0.0  1538  1541 00:00:00 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8
 0.0  1538  1542 00:00:00 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8
 0.0  1538  1543 00:00:00 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8
 0.1  1538  1544 00:00:33 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8
 0.0  1538  1545 00:00:12 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8
 0.0  1538  1546 00:00:00 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8
 0.5  1538  1547 00:01:43 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8
 0.0  1538  7170 00:00:00 ?        java -classpath .:/usr/java/jdk1.8.0_131/lib/dt.jar:/usr/java/jdk1.8.0_131/lib/tools.jar:lib/adsl.jar:lib/commons-beanutils-1.8

6.2>将获取的线程号（十进制数）转换成十六进制
    printf "%x\n" 1539
    结果：603
6.3>结合进程号和线程号,利用jstack查到异常代码所在行
    jstack -l 1538| grep 0x603 -A 10
    结果：
       
[root@cloud ~]# jstack -l 1538| grep 0x603 -A 10
"main" #1 prio=5 os_prio=0 tid=0x00007f0088008800 nid=0x603 runnable [0x00007f008ca04000]
   java.lang.Thread.State: RUNNABLE
    at com.xxxx.xxxx.tester.Pppoe.main(Unknown Source)
   Locked ownable synchronizers:
    - None
"VM Thread" os_prio=0 tid=0x00007f008806d000 nid=0x604 runnable         
"VM Periodic Task Thread" os_prio=0 tid=0x00007f00880c0000 nid=0x60b waiting on condition

此时，就可以看到占用CPU的程序了。
        
6.4>将信息输出到文件
    jstack -l 1538 > 1538.pid
    使用vi查看。
    
6.5>注意：需要在多个时间段提出多个 Thread Dump信息，然后综合进行对比分析，单独分析一个文件是没有意义的
